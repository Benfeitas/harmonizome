package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.DatasetDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.BioEntityAlphabetizer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/gene", "/gene/*" })
public class GenePage extends HttpServlet {

	private static final long serialVersionUID = 4256183225988457817L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String query = UrlUtil.getPath(request);
		
		// If the gene is a synonym, we save the original query to notify the
		// user where we are redirecting from.
		String originalQuery = null;
		Gene gene = null;
		boolean isSynonym = false;
		int numCategories = 0;
		int numDatasets = 0;
		String entityList = "";
		
		List<Dataset> datasetsByGene = null;
		
		// We could make another DB query, but the number datasets should
		// never be greater than ~100.
		Set<String> uniqueAttributeGroups = new HashSet<String>();

		try {
			HibernateUtil.beginTransaction();
			if (query != null) {
				gene = GeneDao.getFromSymbol(query);
				if (gene == null) {
					gene = GeneDao.getFromSynonymSymbol(query);
					if (gene != null) {
						isSynonym = true;
						originalQuery = query;
						query = gene.getSymbol();
					}
				}
				if (gene != null) {
					datasetsByGene = DatasetDao.getFromGene(query);
					Collections.sort(datasetsByGene, new BioEntityAlphabetizer());
					for (Dataset dataset : datasetsByGene) {
						uniqueAttributeGroups.add(dataset.getAttributeGroup().getName());
					}
					numCategories = uniqueAttributeGroups.size();
					numDatasets = datasetsByGene.size();
				}
			}
			
			if (gene == null) {
				request.getRequestDispatcher(Constant.TEMPLATE_DIR + "404.jsp").forward(request, response);
			} else {
				StringBuilder groups = new StringBuilder();
				int i = 0;
				for (String group : uniqueAttributeGroups) {
					groups.append(group);
					if (i < uniqueAttributeGroups.size()-1) {
						groups.append(", ");
					}
					i++;
				}
				String allAssociationsSummary = "Associations covering " + numCategories + " categories of biological entities (" + groups.toString() + ")" + entityList + " from " + numDatasets + " datasets";
				request.setAttribute("allAssociationsSummary", allAssociationsSummary);
				request.setAttribute("note", isSynonym ? "Gene; redirected from " + originalQuery : "Gene");
				request.setAttribute("gene", gene);
				request.setAttribute("datasetsByGene", datasetsByGene);
				request.getRequestDispatcher(Constant.TEMPLATE_DIR + "gene.jsp").forward(request, response);
			}
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.close();
		}
	}
}
