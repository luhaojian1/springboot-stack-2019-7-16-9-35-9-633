package com.tw.apistackbase;
import com.tw.apistackbase.controller.EmployeesController;
import com.tw.apistackbase.modle.Employee;
import com.tw.apistackbase.service.EmployeeSerive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        Employee employee = new Employee();
        employee.setId("111");
        employee.setName("小明");
        employee.setAge(18);
        employee.setGender("female");
        employee.setSalary(6000);

        when(employeeSerive.findById(anyString())).thenReturn(employee);
        ResultActions resultActions = mvc.perform(get("/employees/{employeeId}", employee.getId()));
        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.name", is("小明")))
                .andExpect(jsonPath("$.id", is("111"))).andExpect(jsonPath("$.age", is(18)))
                .andExpect(jsonPath("$.gender", is("female"))).andExpect(jsonPath("$.salary", is(6000)));


    }

    @Test
    public void should_return_correct_employee_information_when_delete_employee() throws Exception {

        Employee employee = new Employee();
        employee.setId("111");
        employee.setName("小明");
        employee.setAge(20);
        employee.setGender("female");
        employee.setSalary(6000);

        when(employeeSerive.deleteEmployee(anyString())).thenReturn(employee);
        ResultActions resultActions = mvc.perform(delete("/employees/{employeeId}", employee.getId()));
        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.name", is("小明")))
                .andExpect(jsonPath("$.id", is("111"))).andExpect(jsonPath("$.age", is(20)))
                .andExpect(jsonPath("$.gender", is("female"))).andExpect(jsonPath("$.salary", is(6000)));
    }


    @Test
    public void should_return_correct_employee_list_size_when_findAll_employee() throws Exception {

        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setId("111");
        employee.setName("小明");
        employee.setAge(20);
        employee.setGender("female");
        employee.setSalary(6000);
        employees.add(new Employee());

        when(employeeSerive.findAll()).thenReturn(employees);
        ResultActions resultActions = mvc.perform(get("/employees"));
        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));


    }

    @Test
    public void should_return_correct_employee_list_when_findAll_employee() throws Exception {

        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setId("111");
        employee.setName("小明");
        employee.setAge(20);
        employee.setGender("female");
        employee.setSalary(6000);
        employees.add(employee);
        employees.add(new Employee());

        when(employeeSerive.findSuitableEmployeesByAge(anyInt())).thenReturn(employees);
        ResultActions resultActions = mvc.perform(get("/employees"));
        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }
}
