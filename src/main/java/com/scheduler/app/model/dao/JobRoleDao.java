package com.scheduler.app.model.dao;

import com.scheduler.app.model.entity.JobRolePojo;

import java.util.List;

public interface JobRoleDao {
    List<JobRolePojo> selectAllRecords();
    List<JobRolePojo> selectByTitleJobTitle();
    List<JobRolePojo> selectByDepartment();

    boolean insertJobRole(JobRolePojo jobRole);
    boolean updateJobRole(JobRolePojo jobRole);
    boolean deleteJobRole(JobRolePojo jobRole);
}
