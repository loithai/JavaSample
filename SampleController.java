/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.data.jpa.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import sample.data.jpa.domain.City;
import sample.data.jpa.domain.SwamUser;
import sample.data.jpa.domain.UserRole;
import sample.data.jpa.service.CitySearchCriteria;
import sample.data.jpa.service.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sample.data.jpa.service.SwamUserService;
import sample.data.jpa.service.UserRoleService;

import java.util.List;
import java.util.Set;

@Controller
public class SampleController {

	@Autowired
	private CityService cityService;

	@Autowired
	private SwamUserService swamUserService;

	@Autowired
	private UserRoleService userRoleService;

	@GetMapping("/city")
	@ResponseBody
	@Transactional(readOnly = false)
	public List<City> helloWorld() {
		SwamUser user = new SwamUser("Loi");
		UserRole role = new UserRole("Admin");
		role.setUser(user);
		UserRole role2 = new UserRole("User");
		role2.setUser(user);
		Set<UserRole> roles = user.getRoles();
		roles.add(role2);
		swamUserService.saveUser(user);
		userRoleService.saveRole(role);
		userRoleService.saveRole(role2);
		System.out.println(user.getId() + " -> " + role.getId());
		List<City> cities = this.cityService.getCities();
		return cities;
	}

	@GetMapping("/city/{name}")
	@ResponseBody
	@Transactional(readOnly = true)
	public Page<City> helloWorld(@PathVariable String name) {
		CitySearchCriteria search = new CitySearchCriteria();
		search.setName(name);
		search.setCountry("UK");
		Pageable page = creaetPageRequest();
		Page<City> cities = this.cityService.findCities(search, page);
		return cities;
	}

	@GetMapping("/citycount")
	@ResponseBody
	@Transactional(readOnly = true)
	public long cityCount() {
		CitySearchCriteria search = new CitySearchCriteria();
		Pageable page = creaetPageRequest();
		Page<City> cities = this.cityService.findCities(search, page);
		return cities.getTotalElements();
	}

	private Pageable creaetPageRequest() {
		return new PageRequest(0, 10);
	}

}
