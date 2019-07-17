package com.tw.apistackbase;

import com.tw.apistackbase.controller.CompanyController;
import com.tw.apistackbase.modle.Company;
import com.tw.apistackbase.modle.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CompanyService companyService;

    @Test
    public void should_return_correct_company_when_find_by_id() throws Exception {
        Company company = new Company();
        company.setCompanyId("a123");
        company.setCompanyName("CVTE");
        company.setEmployeesNumber(200);
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee("111", "小明", "female", 20, 6000);
        employees.add(employee);
        company.setEmployees(employees);

        when(companyService.findById(anyString())).thenReturn(company);
        ResultActions resultActions = mvc.perform(get("/companies/{companyId}", company.getCompanyId()));
        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.companyName", is("CVTE")))
                .andExpect(jsonPath("$.companyId", is("a123")))
                .andExpect(jsonPath("$.employeesNumber", is(200)));
    }

    @Test
    public void should_return_correct_companies_when_findAll() throws Exception {
        Company company = new Company();
        company.setCompanyId("a123");
        company.setCompanyName("CVTE");
        company.setEmployeesNumber(200);
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee("111", "xiaoming", "female", 20, 6000);
        employees.add(employee);
        company.setEmployees(employees);
        List<Company> companies = new ArrayList<>();
        companies.add(company);

        when(companyService.findAll()).thenReturn(companies);
        ResultActions resultActions = mvc.perform(get("/companies"));
        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$[0].companyName", is("CVTE")))
                .andExpect(jsonPath("$[0].companyId", is("a123")))
                .andExpect(jsonPath("$[0].employeesNumber", is(200)))
                .andExpect(jsonPath("$[0].employees", hasSize(1)));
    }

    /*
        @Test
        public void should_return_correct_company_when_updateCompany() throws Exception {
            Company company = new Company();
            company.setCompanyId("a123");
            company.setCompanyName("CVTE");
            company.setEmployeesNumber(200);
            List<Employee> employees = new ArrayList<>();
            Employee employee = new Employee("111", "xiaoming", "female", 20, 6000);
            employees.add(employee);
            company.setEmployees(employees);


            when(companyService.updateCompany(ArgumentMatchers.any())).thenReturn(company);
            ResultActions resultActions = mvc.perform(put("/companies/{companyId}",anyString()).contentType(MediaType.APPLICATION_JSON).content("{\n" +
                    "        \"companyId\": \"a123\",\n" +
                    "        \"companyName\": \"CVTE\",\n" +
                    "        \"employeesNumber\": 200,\n" +
                    "    }"));
            resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.companyName", is("CVTE")))
                    .andExpect(jsonPath("$.companyId", is("a123")))
                    .andExpect(jsonPath("$.employeesNumber", is(200)))
                    .andExpect(jsonPath("$.employees", hasSize(1)));
        }
    */
    @Test
    public void should_return_correct_company_when_createCompany() throws Exception {
        Company company = new Company();
        company.setCompanyId("a123");
        company.setCompanyName("CVTE");
        company.setEmployeesNumber(200);
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee("111", "xiaoming", "female", 20, 6000);
        employees.add(employee);
        company.setEmployees(employees);


        when(companyService.createCompany(ArgumentMatchers.any())).thenReturn(company);
        ResultActions resultActions = mvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "        \"companyId\": \"a123\",\n" +
                "        \"companyName\": \"CVTE\",\n" +
                "        \"employeesNumber\": 200\n" +
                "    }"));
        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.companyName", is("CVTE")))
                .andExpect(jsonPath("$.companyId", is("a123")))
                .andExpect(jsonPath("$.employeesNumber", is(200)))
                .andExpect(jsonPath("$.employees", hasSize(1)));
    }
}