package tapp.model.sgc;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.persistent.utils.excel.ExcelColumn;
import com.persistent.utils.excel.ExcelReport;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@ExcelReport(reportName="Client.xls")
public class Client implements Cloneable, Serializable {

	@Persistent
	private String title;
	@Persistent(primaryKey = "true",valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String lastname;
	@Persistent
    private String firstname;
	@Persistent
    private String commonName;
	@Persistent
    private String passwd;
	@Persistent
    private String phone;
	@Persistent
    private String seeAlso;
	@Persistent
    private String desc;
	@Persistent
    private String lastaddress;
	@Persistent
    private String lastzip;
	@Persistent
    private String laststate;
	@Persistent
    private String lastcountry;	 //design for the future, assuming you go international one day ! :)

	@ExcelColumn(label="Primary Phone")
    public Long getId() {
		return id;
	}
//	@Validate("required,min=1")
	public void setId(Long id) {
		this.id = id;
	}

	@ExcelColumn(label="Last Name")
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	@ExcelColumn(label="First Name")
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	@ExcelColumn(label="Common Name")
	public String getCommonName() {
		return commonName;
	}
	
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	
	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@ExcelColumn(label="Phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@ExcelColumn(label="See Also")
	public String getSeeAlso() {
		return seeAlso;
	}

	public void setSeeAlso(String seeAlso) {
		this.seeAlso = seeAlso;
	}
	
	@ExcelColumn(label="Description")
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@ExcelColumn(label="Last Address")
	public String getLastaddress() {
		return lastaddress;
	}
	
	public void setLastaddress(String lastaddress) {
		this.lastaddress = lastaddress;
	}
	
	@ExcelColumn(label="Last Zipcode")	
	public String getLastzip() {
		return lastzip;
	}
	
	public void setLastzip(String lastzip) {
		this.lastzip = lastzip;
	}

	@ExcelColumn(label="Last State")
	public String getLaststate() {
		return laststate;
	}
	
	public void setLaststate(String laststate) {
		this.laststate = laststate;
	}
	
	@ExcelColumn(label="Last Country")
	public String getLastcountry() {
		return lastcountry;
	}
	
	public void setLastcountry(String lastcountry) {
		this.lastcountry = lastcountry;
	}
	
	@ExcelColumn(label="Title")	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public Object clone() throws CloneNotSupportedException {
		Client clone = (Client)super.clone();

		return clone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commonName == null) ? 0 : commonName.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastaddress == null) ? 0 : lastaddress.hashCode());
		result = prime * result
				+ ((lastcountry == null) ? 0 : lastcountry.hashCode());
		result = prime * result
				+ ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result
				+ ((laststate == null) ? 0 : laststate.hashCode());
		result = prime * result + ((lastzip == null) ? 0 : lastzip.hashCode());
		result = prime * result + ((passwd == null) ? 0 : passwd.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((seeAlso == null) ? 0 : seeAlso.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (commonName == null) {
			if (other.commonName != null)
				return false;
		} else if (!commonName.equals(other.commonName))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastaddress == null) {
			if (other.lastaddress != null)
				return false;
		} else if (!lastaddress.equals(other.lastaddress))
			return false;
		if (lastcountry == null) {
			if (other.lastcountry != null)
				return false;
		} else if (!lastcountry.equals(other.lastcountry))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (laststate == null) {
			if (other.laststate != null)
				return false;
		} else if (!laststate.equals(other.laststate))
			return false;
		if (lastzip == null) {
			if (other.lastzip != null)
				return false;
		} else if (!lastzip.equals(other.lastzip))
			return false;
		if (passwd == null) {
			if (other.passwd != null)
				return false;
		} else if (!passwd.equals(other.passwd))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (seeAlso == null) {
			if (other.seeAlso != null)
				return false;
		} else if (!seeAlso.equals(other.seeAlso))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Client [commonName=" + commonName + ", desc=" + desc
				+ ", firstname=" + firstname + ", id=" + id + ", lastaddress="
				+ lastaddress + ", lastcountry=" + lastcountry + ", lastname="
				+ lastname + ", laststate=" + laststate + ", lastzip="
				+ lastzip + ", passwd=" + passwd + ", phone=" + phone
				+ ", seeAlso=" + seeAlso + ", title=" + title + "]";
	}
		
}
