package com.huangdh.springboot_vue.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author jason852
 * @since 2023-03-29
 */
@TableName("t_student")
@ApiModel(value = "Student对象", description = "")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "N_STUDENT_ID", type = IdType.AUTO)
      private Integer nStudentId;

    private String vcStudentCode;

    private String vcStudentName;

    private Integer nSex;

    private Integer nGrade;

    private Integer nMajor;

    private String vcDetail;

    
    public Integer getnStudentId() {
        return nStudentId;
    }

      public void setnStudentId(Integer nStudentId) {
          this.nStudentId = nStudentId;
      }
    
    public String getVcStudentCode() {
        return vcStudentCode;
    }

      public void setVcStudentCode(String vcStudentCode) {
          this.vcStudentCode = vcStudentCode;
      }
    
    public String getVcStudentName() {
        return vcStudentName;
    }

      public void setVcStudentName(String vcStudentName) {
          this.vcStudentName = vcStudentName;
      }
    
    public Integer getnSex() {
        return nSex;
    }

      public void setnSex(Integer nSex) {
          this.nSex = nSex;
      }
    
    public Integer getnGrade() {
        return nGrade;
    }

      public void setnGrade(Integer nGrade) {
          this.nGrade = nGrade;
      }
    
    public Integer getnMajor() {
        return nMajor;
    }

      public void setnMajor(Integer nMajor) {
          this.nMajor = nMajor;
      }
    
    public String getVcDetail() {
        return vcDetail;
    }

      public void setVcDetail(String vcDetail) {
          this.vcDetail = vcDetail;
      }

    @Override
    public String toString() {
        return "Student{" +
              "nStudentId=" + nStudentId +
                  ", vcStudentCode=" + vcStudentCode +
                  ", vcStudentName=" + vcStudentName +
                  ", nSex=" + nSex +
                  ", nGrade=" + nGrade +
                  ", nMajor=" + nMajor +
                  ", vcDetail=" + vcDetail +
              "}";
    }
}
