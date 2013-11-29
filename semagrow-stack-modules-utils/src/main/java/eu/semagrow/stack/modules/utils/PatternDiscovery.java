package eu.semagrow.stack.modules.utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Antonis Koukourikos
 *
 */
public interface PatternDiscovery {

	/**
	 * @return A list of equivalent URIs aligned with a certain confidence with the initial URI and belonging to a specific schema
	 */
	public ArrayList<EquivalentURI> retrieveEquivalentPatterns()
			throws IOException, ClassNotFoundException, SQLException;

}