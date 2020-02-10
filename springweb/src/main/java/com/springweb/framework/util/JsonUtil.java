package com.springweb.framework.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	/**
	 * jsonString - > map
	 * @param jsonString
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object> getMapFromJsonStr(String jsonString) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(df);
		Map<String, Object> map = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>(){});
		return map;
	}

}
