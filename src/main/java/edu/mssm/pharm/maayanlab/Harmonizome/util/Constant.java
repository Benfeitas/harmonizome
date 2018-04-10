package edu.mssm.pharm.maayanlab.Harmonizome.util;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

public class Constant {

	public static final Properties props = new Properties();
	static {
        try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream resource = loader.getResourceAsStream("constant.properties");
			System.out.println(resource);
			props.load(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final String HARMONIZOME_PREFIX = Constant.props.getProperty("HARMONIZOME_PREFIX");

	public static final String HARMONIZOME = "Harmonizome";


/* URLs
 *---------------------------------------------------------------------------*/

	public static final String API_URL = "api/1.0";

	// Undocumented API for administrative tasks.
	public static final String API_DARK_URL = "api/dark";

	public static final String API_CURSOR = "cursor";

	public static final String DOWNLOAD_COUNT_URL = API_DARK_URL + "/download_count";
	
	public static final String DOWNLOAD_ASSOCIATIONS_URL = API_URL + "/download/associations";

    public static final String VISUALIZE_URL = "visualize";

	public static final String HEAT_MAPS_URL = VISUALIZE_URL + "/heat_map";

    public static final String HEAT_MAPS_API_URL = API_URL + "/" + VISUALIZE_URL + "/heat_map";

	public static final String ADMIN_URL = "admin";
	
	public static final String ML_URL = "machine_learning";

	public static final String HARMONIZOME_ML_URL = "/Harmonizome-ML/";

	// This has no preceding slash because we typically use it as a relative URL in JSP templates.
	public static final String SEARCH_URL = "search";
	
	public static final String SUGGEST_URL = "suggest";

	static final String getSEARCH_URL() {
		return SEARCH_URL;
	}	


/* Directories
 *---------------------------------------------------------------------------*/
	
	public static final String DATA_DIR = "http://amp.pharm.mssm.edu/static/hdfs/harmonizome/data";
	
	public static final String ML_DATA_DIR = "http://amp.pharm.mssm.edu/static/hdfs/harmonizome/data/machine_learning";

	public static final String HEAT_MAP_IMAGES_DIR = "http://amp.pharm.mssm.edu/static/harmonizome/heat_maps/";
	
	public static final String TEMPLATE_DIR = "/template/";


/* Miscellaneous
 *---------------------------------------------------------------------------*/

	public static final String REST_LOCATION_PROP = "href";

	public static final String ENTITY_TYPE = "entity";
	
	public static final int API_MAX_RESULTS = 100;

    public static final int DB_MAX_RESULTS = 1000;
    
    public static final int ML_PREDICTIONS_MAX_RESULTS = 10000;
}
