/**
 * 
 * Copyright 2016 Surajit Paul
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.phoenix.data.transform.adapter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;


/**
 * 
 * @author Surajit Paul
 *
 */
public class MaterialsAdapter extends TypeAdapter<Materials>  implements JsonSerializer<Materials>, JsonDeserializer<Materials>, OnlineProperties {

	@Override
	public Materials read(final JsonReader in) throws IOException {
		final Materials materials = new Materials();
		List<Material> materialList = new ArrayList<Material>();

		in.beginObject();
		while(in.hasNext()){						
			if(in.peek() == JsonToken.NAME){
				String jsonTag = in.nextName();
				if(JCR_PRIMARYTYPE.equals(jsonTag)){
					materials.setJcrPrimaryType(in.nextString());
				}
				else if(JSON_KEY_N1.equalsIgnoreCase(jsonTag) || JSON_KEY_N2.equalsIgnoreCase(jsonTag) 
						|| JSON_KEY_N3.equalsIgnoreCase(jsonTag) || JSON_KEY_N4.equalsIgnoreCase(jsonTag)
						|| JSON_KEY_N5.equalsIgnoreCase(jsonTag) || JSON_KEY_N6.equalsIgnoreCase(jsonTag)
						|| JSON_KEY_N7.equalsIgnoreCase(jsonTag) || JSON_KEY_S0.equalsIgnoreCase(jsonTag)){
					materialList.add(readMaterial(in));
				}
				else{
					in.skipValue();
				}
			}			
		}
		materials.setMaterial(materialList);
		in.endObject();
		return materials;
	}

	/**
	 * 
	 * @param jr
	 * @return
	 * @throws IOException
	 */
	public Material readMaterial(JsonReader jr) throws IOException{
		Material material = new Material();

		jr.beginObject();
		while(jr.hasNext()){
			String jsonTag = jr.nextName();

			switch(jsonTag){			
			case MATERIALNAME: 
				material.setMaterialName(jr.nextString());
				break;
			case JCR_LASTMODIFIEDBY: 
				material.setJcrLastModifiedBy(jr.nextString());
				break;
			case MATERIALURL: 
				material.setMaterialUrl(jr.nextString());
				break;
			case MATERIALTYPE: 
				material.setMaterialType(jr.nextString());
				break;
			case SLING_RESOURCETYPE: 
				material.setSlingResourceType(jr.nextString());
				break;		
			case JCR_LASTMODIFIED: 
				material.setJcrLastModified(jr.nextString());
				break;
			case JCR_PRIMARYTYPE: 
				material.setJcrPrimaryType(jr.nextString());
				break;			
			default:				
				throw new IllegalArgumentException("New attribute added to JSON object: " + jsonTag);
			}			
		}
		jr.endObject();
		return material;
	}


	@Override
	public void write(JsonWriter out, final Materials materials) throws IOException {
		out.beginObject();
		if(materials != null){
			out.name(JCR_PRIMARYTYPE).value(materials.getJcrPrimaryType());
			if(materials.getMaterial() != null){
				out.name(MATERIAL);
				writeMaterial(out, materials.getMaterial());
			}
			else{
				out.name(MATERIAL).nullValue();
			}
		}		
		out.endObject();
	}

	/**
	 * 
	 * @param writer
	 * @param materials
	 * @throws IOException
	 */
	public void writeMaterial(JsonWriter writer, List<Material> materials) throws IOException {
		writer.beginArray();
		for(Material material: materials){
			writer.beginObject();
			writer.name(MATERIALNAME).value(material.getMaterialName());
			writer.name(JCR_LASTMODIFIEDBY).value(material.getJcrLastModifiedBy());
			writer.name(MATERIALURL).value(material.getMaterialUrl());
			writer.name(MATERIALTYPE).value(material.getMaterialType());
			writer.name(SLING_RESOURCETYPE).value(material.getSlingResourceType());
			writer.name(JCR_LASTMODIFIED).value(material.getJcrLastModified());
			writer.name(JCR_PRIMARYTYPE).value(material.getJcrPrimaryType());					
			writer.endObject();
		}
		writer.endArray();
	}

	@Override
	public JsonElement serialize(Materials materials, Type type,
			JsonSerializationContext ctx) {
		JsonObject jo = new JsonObject();
		jo.add(JCR_PRIMARYTYPE, new JsonPrimitive(materials.getJcrPrimaryType()));
		List<Material> materialList = materials.getMaterial();
		if(materialList != null && materialList.size() > 0){
			for(Material material: materialList){
				jo.add("materialName", new JsonPrimitive(material.getMaterialName()));
				jo.add("lastModifiedBy", new JsonPrimitive(material.getJcrLastModifiedBy()));
				jo.add("materialUrl", new JsonPrimitive(material.getMaterialUrl()));
				jo.add("materialType", new JsonPrimitive(material.getMaterialType()));
				jo.add("resourceType", new JsonPrimitive(material.getSlingResourceType()));
				jo.add("lastModified", new JsonPrimitive(material.getJcrLastModified()));
				jo.add("primaryType", new JsonPrimitive(material.getJcrPrimaryType()));				
			}
		}		
		return jo;
	}

	
	@Override	
	public Materials deserialize(JsonElement json, Type type,
			JsonDeserializationContext dctx) throws JsonParseException {
		JsonObject jo = (JsonObject) json;
		JsonArray jsonArray = new JsonArray();
		Materials materials = new Materials();
		List<Material> materialList = new ArrayList<Material>();
		Material material = new Material();
		materials.setJcrPrimaryType(jo.get(JCR_PRIMARYTYPE).getAsString());
		jsonArray = jo.get(MATERIAL).getAsJsonArray();
		Iterator<JsonElement> arrayIterator = jsonArray.iterator();
		while(arrayIterator.hasNext()){
			JsonObject jsonObj = (JsonObject) arrayIterator.next();
			material = new Material();
			material.setMaterialName(jsonObj.get(MATERIALNAME).getAsString());
			material.setJcrLastModifiedBy(jsonObj.get(JCR_LASTMODIFIEDBY).getAsString());
			material.setMaterialUrl(jsonObj.get(MATERIALURL).getAsString());
			material.setMaterialType(jsonObj.get(MATERIALTYPE).getAsString());
			material.setSlingResourceType(jsonObj.get(SLING_RESOURCETYPE).getAsString());
			material.setJcrLastModified(jsonObj.get(JCR_LASTMODIFIED).getAsString());
			material.setJcrPrimaryType(jsonObj.get(JCR_PRIMARYTYPE).getAsString());			
			materialList.add(material);
		}
		materials.setMaterial(materialList);
		return materials;
	}
}
