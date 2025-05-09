package com.ncu.usermatchserver.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName tags
 */
@TableName(value ="tags")
@Data
public class Tags implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标签名称
     */
    private String tagsName;

    /**
     * 创建用户的主键
     */
    private Long userId;

    /**
     * 父标签id
     */
    private Long parent;

    /**
     * 是否为父标签
     */
    private Integer isParent;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新时间
     */
    private Date update_time;

    /**
     * 是否删除 0-没被删除
     */
    @TableLogic
    private Integer is_deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Tags other = (Tags) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTagsName() == null ? other.getTagsName() == null : this.getTagsName().equals(other.getTagsName()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getParent() == null ? other.getParent() == null : this.getParent().equals(other.getParent()))
            && (this.getIsParent() == null ? other.getIsParent() == null : this.getIsParent().equals(other.getIsParent()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
            && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
            && (this.getIs_deleted() == null ? other.getIs_deleted() == null : this.getIs_deleted().equals(other.getIs_deleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTagsName() == null) ? 0 : getTagsName().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getParent() == null) ? 0 : getParent().hashCode());
        result = prime * result + ((getIsParent() == null) ? 0 : getIsParent().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getIs_deleted() == null) ? 0 : getIs_deleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tagsName=").append(tagsName);
        sb.append(", userId=").append(userId);
        sb.append(", parent=").append(parent);
        sb.append(", isParent=").append(isParent);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", is_deleted=").append(is_deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}