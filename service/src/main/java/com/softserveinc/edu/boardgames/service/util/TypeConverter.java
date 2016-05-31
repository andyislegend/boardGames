package com.softserveinc.edu.boardgames.service.util;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Andrii Petryk
 * 
 * This class is used to convert enums to other types
 *
 */
public class TypeConverter {

	/**
     * Converts set of enums to set of their string equivalents
     *
     * @param enums set witch need to be converted
     * @return set of string equivalents
     */
	public static <T extends Enum<T>> Set<String> enumToString(Set<T> enums) {

		Set <String> set = new HashSet<>();
		
		for (T string : enums) {
			set.add(string.name());
		}
		
        return set;
    }
	
}
