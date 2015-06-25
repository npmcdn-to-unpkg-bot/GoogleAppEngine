package tapp.model.sci;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.compass.annotations.SearchableId;

import com.google.appengine.api.datastore.Text;
import com.persistent.utils.excel.ExcelColumn;
import com.persistent.utils.excel.ExcelReport;

/*
 * Codesion web hooks listerner (https://app.codesion.com/ajax#services/confsvncommit)
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
@ExcelReport(reportName="Codesion.xls")
public class RepositoryData implements Cloneable, Serializable {

	@Persistent(primaryKey = "true",valueStrategy=IdGeneratorStrategy.IDENTITY)
    @SearchableId(name = "codesion")
	private Long id;
    @SearchableId(name = "codesion")
	private String service; //'svn' for Subversion services. May take other values in future.
    @SearchableId(name = "codesion")
	private String author; //login name of the person who made the commit
    @SearchableId(name = "codesion")
	private String project; //short name of the project being committed to
    @SearchableId(name = "codesion")
	private String organization; //short name of the organization who owns the project (AKA domain)
    @SearchableId(name = "codesion")
	private String youngest; //Newest revision number in svn
    @SearchableId(name = "codesion")
	private String log; //The log message entered by the user for this commit
    @SearchableId(name = "codesion")
	private Text changed; //List of what files are changed, as listed by svn.
    @SearchableId(name = "codesion")
	private Date changedDate; //List of what files are changed, as listed by svn.

    @ExcelColumn(label="Id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ExcelColumn(label="Service")
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	@ExcelColumn(label="Author")
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@ExcelColumn(label="Project")
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	@ExcelColumn(label="Organization")
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	@ExcelColumn(label="Youngest")
	public String getYoungest() {
		return youngest;
	}
	public void setYoungest(String youngest) {
		this.youngest = youngest;
	}
	@ExcelColumn(label="Comment")
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	@ExcelColumn(label="Changed")
	public Text getChanged() {
		return changed;
	}
	public void setChanged(Text changed) {
		this.changed = changed;
	}
	
    @ExcelColumn(label="Date")
	public Date getChangedDate() {
		return changedDate;
	}
	public void setChangedDate(Date changedDate) {
		this.changedDate = changedDate;
	}
	
	@Override
	public String toString() {
		return "RepositoryData [author=" + author + ", changed=" + changed
				+ ", changedDate=" + changedDate + ", id=" + id + ", log="
				+ log + ", organization=" + organization + ", project="
				+ project + ", service=" + service + ", youngest=" + youngest
				+ "]";
	}
	
	public Object clone() throws CloneNotSupportedException {
		RepositoryData clone = (RepositoryData)super.clone();

		return clone;
	}
	
}
