package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 文件上传 
 *
 */
@Table(name = "file_upload")
public class FileUpload implements Serializable {
	private static final long serialVersionUID = 5055139898654620738L;
	@Id
	@GeneratedValue(generator = "JDBC")
	private int id;
	private String fileName;
	private String filePath;
	private int createBy;
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getCreateBy() {
		return createBy;
	}

	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "FileUpload [id=" + id + ", fileName=" + fileName
				+ ", filePath=" + filePath + ", createBy=" + createBy
				+ ", createTime=" + createTime + "]";
	}

}
