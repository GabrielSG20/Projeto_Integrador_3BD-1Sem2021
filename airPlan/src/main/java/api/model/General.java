package api.model;


import javax.persistence.Entity;

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
@Component
public class General {
        
        private String flg_secundary;
        
        private Long cdl_section;
        
        private String cdl_sub_section;
        
        private Long cdl_block;
        
        private String cdl_block_name;
        
        private Long cdl_code;
        
        private String flg_tag;
        
        private String mnl_name;
        
        public General(){

        }
    }
