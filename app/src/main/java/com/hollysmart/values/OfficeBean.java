package com.hollysmart.values;

import java.io.Serializable;

/**
 * Created by sunpengfei on 2017/8/7.
 */

public class OfficeBean implements Serializable {
    private static final long serialVersionUID = 1L;
    //    private Area area;		    // 归属区域
    private String code; 	            // 机构编码
    //	private String name; 	        // 机构名称
//	private Integer sort;		        // 排序
    private String type; 	            // 机构类型（1：公司；2：部门；3：小组）

    protected String name; 	            // 机构名称

    protected String remarks;	        // 备注

    /*
    --字典表中改为:A:机构节点，B：处室，
    C：融担公司，D：小贷公司，E：银行，F：其它
    */
    private String grade; 	            // 机构等级（1：一级；2：二级；3：三级；4：四级）
    private String address;             // 联系地址
    private String zipCode;             // 邮政编码
    private String master; 	            // 负责人
    private String phone; 	            // 电话
    private String fax; 	            // 传真
    private String email; 	            // 邮箱
    private String useable;             //是否可用
    //    private User primaryPerson;   //联系人
//    private User deputyPerson;        //法人
//    private List<String> childDeptList;//快速添加子部门
    private String licenseId;		    // 营业执照编号
    private String officeAddress;		// 办公地址
    private String registeredAddress;	// 注册地址
    private Integer carNum;		        // 企业车数量
    private String taskArea;		    // 作业范围
    private String areaType;		    // 所属区域

    private String socialCreditnum;		// 统一社会信用代码
    private String organizationCode;	// 织机构代码
    private String shortname;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }

    public String getTaskArea() {
        return taskArea;
    }

    public void setTaskArea(String taskArea) {
        this.taskArea = taskArea;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getSocialCreditnum() {
        return socialCreditnum;
    }

    public void setSocialCreditnum(String socialCreditnum) {
        this.socialCreditnum = socialCreditnum;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
}
