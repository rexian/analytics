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

package org.phoenix.data.transform.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;

public class JsonPrettyPrinter implements PrettyPrinter {

	int count = 0;
	@Override
	public void beforeArrayValues(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {}

	@Override
	public void beforeObjectEntries(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {}

	@Override
	public void writeArrayValueSeparator(JsonGenerator jsonGenerator)
			throws IOException, JsonGenerationException {
		jsonGenerator.writeRaw(','); 
		if (count == 0) { 
			jsonGenerator.writeRaw('\n'); 
		} 
	}

	@Override
	public void writeEndArray(JsonGenerator jsonGenerator, int arg1) throws IOException,
	JsonGenerationException {
		if (count == 0) { 
			jsonGenerator.writeRaw('\n'); 
		} 
	}

	@Override
	public void writeEndObject(JsonGenerator jsonGenerator, int arg1)
			throws IOException, JsonGenerationException {
		count--; 
		jsonGenerator.writeRaw('}'); 
	}

	@Override
	public void writeObjectEntrySeparator(JsonGenerator jsonGenerator)
			throws IOException, JsonGenerationException {
		jsonGenerator.writeRaw(','); 
	}

	@Override
	public void writeObjectFieldValueSeparator(JsonGenerator jsonGenerator)
			throws IOException, JsonGenerationException {
		jsonGenerator.writeRaw(':'); 
	}

	@Override
	public void writeRootValueSeparator(JsonGenerator jsonGenerator) throws IOException,
	JsonGenerationException {
		jsonGenerator.writeRaw('\n'); 
	}

	@Override
	public void writeStartArray(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {}

	@Override
	public void writeStartObject(JsonGenerator jsonGenerator) throws IOException,
	JsonGenerationException {
		count++; 
		jsonGenerator.writeRaw('{'); 
	}
}
