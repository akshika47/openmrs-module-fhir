/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.fhir.providers;

import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.dstu2.resource.Person;
import ca.uhn.fhir.model.dstu2.resource.Practitioner;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import org.openmrs.module.fhir.resources.FHIRPersonResource;

import java.util.List;

public class RestfulPersonResourceProvider implements IResourceProvider {

	;
	private FHIRPersonResource personResource;

	public RestfulPersonResourceProvider() {
		this.personResource = new FHIRPersonResource();
	}

	@Override
	public Class<? extends IResource> getResourceType() {
		return Person.class;
	}

	/**
	 * The "@Read" annotation indicates that this method supports the
	 * read operation. Read operations should return a single resource
	 * instance.
	 *
	 * @param theId The read operation takes one parameter, which must be of type
	 *              IdDt and must be annotated with the "@Read.IdParam" annotation.
	 * @return Returns a resource matching this identifier, or null if none exists.
	 */
	@Read()
	public Person getResourceById(@IdParam IdDt theId) {
		Person result = null;
		result = personResource.getByUniqueId(theId);
		return result;
	}

	/**
	 * Search person by unique id
	 *
	 * @param id object contaning the requested person
	 */
	@Search()
	public List<Person> searchPractitionerByUniqueId(@RequiredParam(name = Practitioner.SP_RES_ID) TokenParam id) {
		return personResource.searchByUniqueId(id);
	}

    @Create()
    public org.openmrs.Person createFHIRPerson(Person person){
        return personResource.createFHIRPerson(person);
    }

}
