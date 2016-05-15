package com.softserveinc.edu.boardgames.persistence.entity.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Convert List<Sting> to Set<UserRoles>
 *     and to the another side.
 *     Use for conversion data, that we got from database
 *     using hql query
 */
public class ConvertSetEnumsToListString {

	/**
     * Convert to List<String> from Set<T>
     * @param enums set with enums
     * @param <T> type of Enum
     * @return
     */
    public static <T extends Enum<T>> List<String> convertToListString(Set<T> enums) {
        
    	List <String> list = new ArrayList<>();
    	
    	for (T string : enums) {
			list.add(string.name());
		}
    	
    	return list;
    }
	
    
//    /**
//     * Convert to Set<T> from List<String>
//     * @param enumStringList
//     * @param clazz type of Enum
//     * @param <T> Enum
//     * @return
//     * @throws IllegalArgumentException
//     */
//    public static <T extends Enum<T>> Set<T> convertToSetUserRole(List<String> enumStringList, Class<T> clazz)
//            throws IllegalArgumentException {
//    	
//        return enumStringList.stream().map(s -> T.valueOf(clazz, s)).collect(Collectors.toSet());
//    }
}
