package ph.fingra.statisticsweb.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Component extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = -9126405583919007966L;
    
    private String componentkey;
    private String appkey;
    private Integer groupkey;
    private String componentname;
    private String changeComponentname;
    private MultipartFile  componentimage;
    private String imagename;
    private String imagepath;
    private byte[] saveByteImage;
    private Date startdate;
    private Date enddate;
    private int isdel =0;
    private Date regdate;
    private Date deldate;
    private String editType; //add or edit
    
    public String getComponentkey() {
        return componentkey;
    }
    public void setComponentkey(String componentkey) {
        this.componentkey = componentkey;
    }
    public String getAppkey() {
        return appkey;
    }
    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
    public Integer getGroupkey() {
        return groupkey;
    }
    public void setGroupkey(Integer groupkey) {
        this.groupkey = groupkey;
    }
    public String getComponentname() {
        return componentname;
    }
    public void setComponentname(String componentname) {
        this.componentname = componentname;
    }
    public String getChangeComponentname() {
        return changeComponentname;
    }
    public void setChangeComponentname(String changeComponentname) {
        this.changeComponentname = changeComponentname;
    }
    public MultipartFile getComponentimage() {
        return componentimage;
    }
    public void setComponentimage(MultipartFile componentimage) {
        this.componentimage = componentimage;
    }
    public String getImagename() {
        return imagename;
    }
    public void setImagename(String imagename) {
        this.imagename = imagename;
    }
    public String getImagepath() {
        return imagepath;
    }
    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
    public byte[] getSaveByteImage() {
        return saveByteImage;
    }
    public void setSaveByteImage(byte[] saveByteImage) {
        this.saveByteImage = saveByteImage;
    }
    public Date getStartdate() {
        return startdate;
    }
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }
    public Date getEnddate() {
        return enddate;
    }
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
    public int getIsdel() {
        return isdel;
    }
    public void setIsdel(int isdel) {
        this.isdel = isdel;
    }
    public Date getRegdate() {
        return regdate;
    }
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }
    public Date getDeldate() {
        return deldate;
    }
    public void setDeldate(Date deldate) {
        this.deldate = deldate;
    }
    public String getEditType() {
        return editType;
    }
    public void setEditType(String editType) {
        this.editType = editType;
    }
}
