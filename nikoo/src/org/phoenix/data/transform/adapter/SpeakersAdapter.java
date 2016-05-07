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
 */
public class SpeakersAdapter extends TypeAdapter<Speakers> implements JsonSerializer<Speakers>, JsonDeserializer<Speakers>, OnlineProperties{

	@Override
	public Speakers read(final JsonReader in) throws IOException {
		final Speakers speakers = new Speakers();
		List<Speaker> speakerList = new ArrayList<Speaker>();
	
		in.beginObject();
		while(in.hasNext()){						
			if(in.peek() == JsonToken.NAME){
				String jsonTag = in.nextName();				
				if(RESOURCETYPE.equals(jsonTag)){
					speakers.setSlingResourceType(in.nextString());
				}
				else if(PRIMARYTYPE.equals(jsonTag)){
					speakers.setJcrPrimaryType(in.nextString());
				}
				else if(JSON_KEY_N1.equalsIgnoreCase(jsonTag) || JSON_KEY_N2.equalsIgnoreCase(jsonTag) 
						|| JSON_KEY_N3.equalsIgnoreCase(jsonTag) || JSON_KEY_N4.equalsIgnoreCase(jsonTag)
						|| JSON_KEY_N5.equalsIgnoreCase(jsonTag) || JSON_KEY_N6.equalsIgnoreCase(jsonTag)
						|| JSON_KEY_N7.equalsIgnoreCase(jsonTag) || JSON_KEY_S0.equalsIgnoreCase(jsonTag)){
					speakerList.add(readSpeaker(in));					
				}			
				else{
					in.skipValue();
				}
			}
		}
		speakers.setSpeaker(speakerList);		
		in.endObject();
		return speakers;
	}

	/**
	 * 
	 * @param jr
	 * @return
	 * @throws IOException
	 */
	public Speaker readSpeaker(JsonReader jr) throws IOException{
		Speaker speaker = new Speaker();

		jr.beginObject();
		while(jr.hasNext()){
			String jsonTag = jr.nextName();
			switch(jsonTag){

			case FIRST_NAME: 				
				speaker.setFirstName(jr.nextString());
				break;
			case BIOGRAPHY: 				
				speaker.setBiography(jr.nextString());
				break;
			case LASTMODIFIEDBY:				
				speaker.setJcrLastModifiedBy(jr.nextString());
				break;
			case RESOURCETYPE: 				
				speaker.setSlingResourceType(jr.nextString());
				break;
			case IMAGE: 				
				speaker.setImage(jr.nextString());
				break;
			case TYPE:				
				speaker.setType(jr.nextString());
				break;
			case LAST_NAME: 
				speaker.setLastName(jr.nextString());
				break;
			case LASTMODIFIED: 
				speaker.setJcrLastModified(jr.nextString());
				break;
			case PRIMARYTYPE: 
				speaker.setJcrPrimaryType(jr.nextString());
				break;			
			default:
				throw new IllegalArgumentException("Invalid KEY: " + jsonTag);
			}			
		}
		jr.endObject();
		return speaker;
	}


	@Override
	public void write(JsonWriter out, final Speakers speakers) throws IOException {
		if(speakers == null){
			return;
		}
		out.beginObject();
		out.name(RESOURCETYPE).value(speakers.getSlingResourceType());
		out.name(PRIMARYTYPE).value(speakers.getJcrPrimaryType());
		if(speakers.getSpeaker() != null){
			out.name(SPEAKER);
			writeSpeaker(out, speakers.getSpeaker());
		}
		else{
			out.name(SPEAKER).nullValue();
		}
		out.endObject();		
		out.flush();
	}

	/**
	 * 
	 * @param writer
	 * @param speakers
	 * @throws IOException
	 */
	public void writeSpeaker(JsonWriter writer, List<Speaker> speakers) throws IOException {
		writer.beginArray();
		for(Speaker speaker: speakers){
			writer.beginObject();
			writer.name(FIRST_NAME).value(speaker.getFirstName());
			writer.name(BIOGRAPHY).value(speaker.getBiography());
			writer.name(LASTMODIFIEDBY).value(speaker.getJcrLastModifiedBy());
			writer.name(RESOURCETYPE).value(speaker.getSlingResourceType());
			writer.name(IMAGE).value(speaker.getImage());
			writer.name(TYPE).value(speaker.getType());
			writer.name(LAST_NAME).value(speaker.getLastName());
			writer.name(LASTMODIFIED).value(speaker.getJcrLastModified());
			writer.name(PRIMARYTYPE).value(speaker.getJcrPrimaryType());		
			writer.endObject();
		}		
		writer.endArray();
	}

	@Override
	public Speakers deserialize(JsonElement json, Type type,
			JsonDeserializationContext dctx) throws JsonParseException {
		JsonObject jo = (JsonObject) json;
		JsonArray jsonArray = new JsonArray();
		Speakers speakers = new Speakers();
		List<Speaker> speakerList = new ArrayList<Speaker>();
		Speaker speaker = new Speaker();
		speakers.setSlingResourceType(jo.get(RESOURCETYPE).getAsString());
		speakers.setJcrPrimaryType(jo.get(PRIMARYTYPE).getAsString());
		jsonArray = jo.get(SPEAKER).getAsJsonArray();
		Iterator<JsonElement> arrayIterator = jsonArray.iterator();
		while(arrayIterator.hasNext()){
			JsonObject jsonObj = (JsonObject) arrayIterator.next();
			speaker = new Speaker();
			speaker.setFirstName(jsonObj.get(FIRST_NAME).getAsString());
			speaker.setBiography(jsonObj.get(BIOGRAPHY).getAsString());
			speaker.setJcrLastModifiedBy(jsonObj.get(LASTMODIFIEDBY).getAsString());
			speaker.setSlingResourceType(jsonObj.get(RESOURCETYPE).getAsString());
			speaker.setImage(jsonObj.get(IMAGE).getAsString());
			speaker.setType(jsonObj.get(TYPE).getAsString());
			speaker.setLastName(jsonObj.get(LAST_NAME).getAsString());			
			speaker.setJcrLastModified(jsonObj.get(LASTMODIFIED).getAsString());
			speaker.setJcrPrimaryType(jsonObj.get(PRIMARYTYPE).getAsString());
			speakerList.add(speaker);
		}
		speakers.setSpeaker(speakerList);
		return speakers;
	}

	@Override
	public JsonElement serialize(Speakers speakers, Type type,
			JsonSerializationContext ctx) {
		JsonObject jo = new JsonObject();
		jo.add(RESOURCETYPE, new JsonPrimitive(speakers.getSlingResourceType()));
		jo.add(PRIMARYTYPE, new JsonPrimitive(speakers.getJcrPrimaryType()));
		List<Speaker> speakerList = speakers.getSpeaker();
		if(speakerList != null && speakerList.size() > 0){
			for(Speaker speaker: speakerList){
				jo.add(FIRST_NAME, new JsonPrimitive(speaker.getFirstName()));
				jo.add(BIOGRAPHY, new JsonPrimitive(speaker.getBiography()));
				jo.add(LASTMODIFIEDBY, new JsonPrimitive(speaker.getJcrLastModifiedBy()));
				jo.add(RESOURCETYPE, new JsonPrimitive(speaker.getSlingResourceType()));
				jo.add(IMAGE, new JsonPrimitive(speaker.getImage()));
				jo.add(TYPE, new JsonPrimitive(speaker.getType()));
				jo.add(LAST_NAME, new JsonPrimitive(speaker.getLastName()));				
				jo.add(LASTMODIFIED, new JsonPrimitive(speaker.getJcrLastModified()));
				jo.add(PRIMARYTYPE, new JsonPrimitive(speaker.getJcrPrimaryType()));				
			}
		}		
		return jo;
	}
}
