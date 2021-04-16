package api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "flag")
@Component
public class FlagModel {
    @Id
    @Column(name = "flg_secundary")
    private String flg_secundary;
    
    @Column(name = "flg_tag")
    private String flg_tag;

    public FlagModel(){

    }
}
