package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Feature;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSynonym;

public class GeneSerializer implements JsonSerializer<Gene> {

	public JsonElement serialize(final Gene gene, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("symbol", new JsonPrimitive(gene.getSymbol()));
		JsonArray synonyms = new JsonArray();
		for (GeneSynonym syn : gene.getSynonyms()) {
			synonyms.add(new JsonPrimitive(syn.getSymbol()));
		}
		if (synonyms.size() != 0) {
			result.add("synonyms", synonyms);
		}
		if (gene.getName() != null) {
			result.add("name", new JsonPrimitive(gene.getName()));
		}
		if (gene.getDescription() != null) {
			result.add("description", new JsonPrimitive(gene.getDescription()));
		}
		if (gene.getNcbiEntrezGeneId() != null) {
			result.add("ncbiEntrezGeneId", new JsonPrimitive(gene.getNcbiEntrezGeneId()));
		}
		if (gene.getNcbiEntrezGeneUrl() != "") {
			result.add("ncbiEntrezGeneUrl", new JsonPrimitive(gene.getNcbiEntrezGeneUrl()));
		}
		
		List<GeneSet> geneSets = new ArrayList<GeneSet>();
		for (Feature feature : gene.getFeatures()) {
			Attribute attribute = feature.getAttribute();
			Dataset dataset = attribute.getDataset();
			GeneSet geneSet = new GeneSet(attribute, dataset);
			geneSets.add(geneSet);
		}
		
		result.add("geneSets", context.serialize(geneSets));
		
		return result;
	}
}