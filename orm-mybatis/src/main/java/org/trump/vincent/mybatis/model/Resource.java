package org.trump.vincent.mybatis.model;

import java.io.Serializable;


/**
 * Created by Vincent on 2018/1/18 0018.
 */
public class Resource implements Serializable {
    protected final static long serialVersionUID = 10000123L;
    private String id;

    private String name;

    private String iconUrl;

    private Boolean usable;

    private String created;

    private Long version;

    private String creatorId;

    private String creatorName;

    private String ccid;

    private String aclContent;

    public String getId() {
        return id;
    }

    public Resource setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Resource setName(String name) {
        this.name = name;
        return this;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public Resource setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public Boolean getUsable() {
        return usable;
    }

    public Resource setUsable(Boolean usable) {
        this.usable = usable;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public Resource setCreated(String created) {
        this.created = created;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public Resource setVersion(Long version) {
        this.version = version;
        return this;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public Resource setCreatorId(String creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public Resource setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public String getCcid() {
        return ccid;
    }

    public Resource setCcid(String ccid) {
        this.ccid = ccid;
        return this;
    }

    public String getAclContent() {
        return aclContent;
    }

    public Resource setAclContent(String aclContent) {
        this.aclContent = aclContent;
        return this;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public Resource setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
        return this;
    }

    private Integer deleteFlag;


}
