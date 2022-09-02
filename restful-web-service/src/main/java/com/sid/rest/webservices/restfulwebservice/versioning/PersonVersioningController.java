package com.sid.rest.webservices.restfulwebservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	@GetMapping("/v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Siddhant Person V1");
	}

	@GetMapping("/v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Siddhant Person V2", "Singh"));
	}

	// Param Versioning

	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 param1() {
		return new PersonV1("Siddhant Person V1");
	}

	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 param2() {
		return new PersonV2(new Name("Siddhant Person V2", "Singh"));
	}

	// Header Versioning

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 header1() {
		return new PersonV1("Siddhant Person V1");
	}

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 header2() {
		return new PersonV2(new Name("Siddhant Person V2", "Singh"));
	}

	// Produces Versioning also called MIME Type Versioning
	
	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 produces1() {
		return new PersonV1("Siddhant Person V1");
	}

	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 produces2() {
		return new PersonV2(new Name("Siddhant Person V2", "Singh"));
	}

}
