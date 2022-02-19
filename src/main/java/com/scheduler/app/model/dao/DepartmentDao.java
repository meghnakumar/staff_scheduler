package com.scheduler.app.model.dao;

import com.scheduler.app.model.entity.DepartmentPojo;

import java.util.List;

public interface DepartmentDao {
    List<DepartmentPojo> selectAllRecords();
    List<DepartmentPojo> selectByDepartmentName();
    List<DepartmentPojo> selectByNumberOfShifts();
    boolean insertDepartment(DepartmentPojo department);
    boolean updateDepartment(DepartmentPojo department);
    boolean deleteDepartment(DepartmentPojo department);
}
