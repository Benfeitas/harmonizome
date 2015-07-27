package edu.mssm.pharm.maayanlab.Harmonizome.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.json.SuggestSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Measurement;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetDeserializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetGroupDeserializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.MeasurementDeserializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.GeneSimpleDeserializer;

public class TestSearchAPIDatasetGroup extends Mockito {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private StringWriter output;
	private PrintWriter writer;
	private Gson gson;

	@Before
	public void setup() throws IOException {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		output = new StringWriter();
		writer = new PrintWriter(output);
		when(response.getWriter()).thenReturn(writer);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetDeserializer());
		gsonBuilder.registerTypeAdapter(DatasetGroup.class, new DatasetGroupDeserializer());
		gsonBuilder.registerTypeAdapter(Measurement.class, new MeasurementDeserializer());
		gsonBuilder.registerTypeAdapter(Gene.class, new GeneSimpleDeserializer());
		gson = gsonBuilder.create();
	}
	
	@Test
	public void testByDataset() throws ServletException, IOException {
		when(request.getParameter("dataset")).thenReturn("GeneSigDB_Published_Gene_Signatures");
		new SuggestAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		SuggestSchema jsonSchema = gson.fromJson(json, SuggestSchema.class);
		List<DatasetGroup> datasetGroups = jsonSchema.getDatasetGroup();
		assertEquals(datasetGroups.get(0).getName(), "omics");
	}
	
	@Test
	public void testByDatasetGroup() throws ServletException, IOException {
		when(request.getParameter("datasetGroup")).thenReturn("omics");
		new SuggestAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		SuggestSchema jsonSchema = gson.fromJson(json, SuggestSchema.class);
		List<DatasetGroup> datasetGroups = jsonSchema.getDatasetGroup();
		assertEquals(datasetGroups.size(), 1);
	}

	@Test
	public void testByMeasurement() throws ServletException, IOException {
		when(request.getParameter("measurement")).thenReturn("text-mining");
		new SuggestAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		SuggestSchema jsonSchema = gson.fromJson(json, SuggestSchema.class);
		List<DatasetGroup> datasetGroups = jsonSchema.getDatasetGroup();
		List<String> validDatasetGroupNames = new ArrayList<String>();
		validDatasetGroupNames.add("structural or functional annotations");
		validDatasetGroupNames.add("disease or phenotype associations");
		for (DatasetGroup dsg : datasetGroups) {
			assertTrue(validDatasetGroupNames.contains(dsg.getName()));
		}
	}
	
	@Test
	public void testByAttributeGroup() throws ServletException, IOException {
		when(request.getParameter("attributeGroup")).thenReturn("chemical");
		new SuggestAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		SuggestSchema jsonSchema = gson.fromJson(json, SuggestSchema.class);
		List<DatasetGroup> datasetGroups = jsonSchema.getDatasetGroup();
		assertEquals(datasetGroups.size(), 3);
		List<String> validDatasetGroupNames = new ArrayList<String>();
		validDatasetGroupNames.add("physical interactions");
		validDatasetGroupNames.add("proteomics");
		validDatasetGroupNames.add("transcriptomics");
		for (DatasetGroup dsg : datasetGroups) {
			assertTrue(validDatasetGroupNames.contains(dsg.getName()));
		}
	}
	
	@Test
	public void testByAttributeType() throws ServletException, IOException {
		when(request.getParameter("attributeType")).thenReturn("cellular_component");
		new SuggestAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		SuggestSchema jsonSchema = gson.fromJson(json, SuggestSchema.class);
		List<DatasetGroup> datasetGroups = jsonSchema.getDatasetGroup();
		List<String> validDatasetGroupNames = new ArrayList<String>();
		validDatasetGroupNames.add("structural or functional annotations");
		validDatasetGroupNames.add("proteomics");
		for (DatasetGroup dsg : datasetGroups) {
			assertTrue(validDatasetGroupNames.contains(dsg.getName()));
		}
	}
}