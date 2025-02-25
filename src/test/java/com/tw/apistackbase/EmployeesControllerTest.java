package com.tw.apistackbase;

import com.tw.apistackbase.controller.EmployeesController;
import com.tw.apistackbase.modle.Employee;
import com.tw.apistackbase.service.EmployeeSerive;
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

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeesController.class)
public class EmployeesControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private EmployeeSerive employeeSerive;

    @Test
    public void should_return_correct_employee_information_when_find_by_id() throws Exception {

        Employee employee = new Employee("111", "小明", "female", 20, 6000);

        when(employeeSerive.findById(anyString())).thenReturn(employee);
        ResultActions resultActions = mvc.perform(get("/employees/{employeeId}", employee.getId()));
        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.name", is("小明")))
                .andExpect(jsonPath("$.id", is("111"))).andExpect(jsonPath("$.age", is(20)))
                .andExpect(jsonPath("$.gender", is("female"))).andExpect(jsonPath("$.salary", is(6000)));


    }

    @Test
    public void should_return_correct_employee_information_when_delete_employee() throws Exception {

        Employee employee = new Employee("111", "小明", "female", 20, 6000);

        when(employeeSerive.deleteEmployee(anyString())).thenReturn(employee);
        ResultActions resultActions = mvc.perform(delete("/employees/{employeeId}", employee.getId()));
        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.name", is("小明")))
                .andExpect(jsonPath("$.id", is("111"))).andExpect(jsonPath("$.age", is(20)))
                .andExpect(jsonPath("$.gender", is("female"))).andExpect(jsonPath("$.salary", is(6000)));
    }


    @Test
    public void should_return_all_employees_when_findAll_employee() throws Exception {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("222", "小明1", "male", 30, 7000));
        employees.add(new Employee("333", "小明1", "male", 30, 7000));

        when(employeeSerive.findAll()).thenReturn(employees);
        ResultActions resultActions = mvc.perform(get("/employees"));
        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));


    }

    @Test
    public void should_return_male_employee_list_when_findEmployeesByGender_given_gender_is_male() throws Exception {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("222", "xiaoming", "male", 20, 7000));

        when(employeeSerive.findEmployeesByGender(anyString())).thenReturn(employees);
        ResultActions resultActions = mvc.perform(get("/employees?gender=male"));
        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(employees.get(0).getId())))
                .andExpect(jsonPath("$[0].name", is(employees.get(0).getName())))
                .andExpect(jsonPath("$[0].age", is(employees.get(0).getAge())))
                .andExpect(jsonPath("$[0].gender", is(employees.get(0).getGender())))
                .andExpect(jsonPath("$[0].salary", is(employees.get(0).getSalary())));
    }

    @Test
    public void should_return_employees_when_findEmployeesByPageAndPageSize_given_page_is_1_and_pageSize_is_5() throws Exception {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("222", "xiaoming", "male", 20, 7000));

        when(employeeSerive.findEmployeesByPageAndPageSize(anyInt(), anyInt())).thenReturn(employees);
        ResultActions resultActions = mvc.perform(get("/employees?page=1&pageSize=5"));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(employees.get(0).getId())))
                .andExpect(jsonPath("$[0].name", is(employees.get(0).getName())))
                .andExpect(jsonPath("$[0].age", is(employees.get(0).getAge())))
                .andExpect(jsonPath("$[0].gender", is(employees.get(0).getGender())))
                .andExpect(jsonPath("$[0].salary", is(employees.get(0).getSalary())));
    }

    @Test
    public void should_return_correct_employee_when_updateEmployee_given_a_modify_employee() throws Exception {

        Employee employee = new Employee("111", "xiaoming", "female", 20, 6000);

        when(employeeSerive.updateEmployee(ArgumentMatchers.any())).thenReturn(employee);
        ResultActions resultActions = mvc.perform(put("/employees/{employeeId}", employee.getId()).contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "        \"id\": \"111\",\n" +
                "        \"name\": \"小明\",\n" +
                "        \"gender\": \"female\",\n" +
                "        \"age\": 18,\n" +
                "        \"salary\": 7000\n" +
                "    }"));
        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.name", is("xiaoming")))
                .andExpect(jsonPath("$.id", is("111"))).andExpect(jsonPath("$.age", is(20)))
                .andExpect(jsonPath("$.gender", is("female"))).andExpect(jsonPath("$.salary", is(6000)));
    }


    @Test
    public void should_return_correct_employee_when_createEmployee_given_a_new_employee() throws Exception {

        Employee employee = new Employee("111", "xiaoming", "female", 20, 6000);

        when(employeeSerive.createEmployee(ArgumentMatchers.any())).thenReturn(employee);
        ResultActions resultActions = mvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "        \"id\": \"111\",\n" +
                "        \"name\": \"小明\",\n" +
                "        \"gender\": \"female\",\n" +
                "        \"age\": 18,\n" +
                "        \"salary\": 7000\n" +
                "    }"));
        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.name", is("xiaoming")))
                .andExpect(jsonPath("$.id", is("111"))).andExpect(jsonPath("$.age", is(20)))
                .andExpect(jsonPath("$.gender", is("female"))).andExpect(jsonPath("$.salary", is(6000)));
    }
}
