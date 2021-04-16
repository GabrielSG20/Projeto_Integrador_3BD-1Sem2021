package api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "codelist")
@Component
public class CodeListModel {
        @Id
        @Column(name = "mnl_id")
        private Long mnl_id;
        
        @Id
        @Column(name = "flg_secundary")
        private String flg_secundary;
        
        @Id
        @Column(name = "cdl_section")
        private Long cdl_section;
        
        @Column(name = "cdl_sub_section")
        private String cdl_sub_section;
        
        @Id
        @Column(name = "cdl_block")
        private Long cdl_block;
        
        @Column(name = "cdl_block_name")
        private String cdl_block_name;
        
        @Column(name = "cdl_code")
        private Long cdl_code;
        
        @Column(name = "cdl_code")
        private Long emp_id;

        public CodeListModel(){

        }

}
