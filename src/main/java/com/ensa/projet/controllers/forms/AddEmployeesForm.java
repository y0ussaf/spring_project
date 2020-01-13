package com.ensa.projet.controllers.forms;

import java.util.List;

public class AddEmployeesForm {
    List<Long> employees_ids;

    public List<Long> getEmployees_ids() {
        return employees_ids;
    }

    public void setEmployees_ids(List<Long> employees_ids) {
        this.employees_ids = employees_ids;
    }
}
