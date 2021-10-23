package crmsystem.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "Loans")
@NamedQuery(name="Voting.Print",query = "Select l From Loan as l")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false,columnDefinition = "INTEGER" )
    private int id;

    @Column(name = "edrpou", nullable = false)
    private String edrpou;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "sum", nullable = false)
    private String sum;
    @Column(name = "documents", columnDefinition = "nvarchar(max)", nullable = false)
    private String documents;
    @Column(name = "status", columnDefinition = "varchar(255) default 'на розгляді'")
    private String status = "на розгляді";
    @Column(name = "manager", columnDefinition = "varchar(255)")
    private String manager;
    @Column(name = "riskmanager", columnDefinition = "varchar(255)")
    private String riskmanager;
    //@Column(name = "datetime", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Column(name = "datetime")
    @CreationTimestamp
    private Timestamp datetime; //= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    @Column(name = "datetimerisk")
    private Timestamp datetimerisk;

    public Loan() {
    }

    public Loan(int id, String edrpou, String name, String sum, String documents, String status, String manager, String riskmanager, Timestamp datetime, Timestamp datetimerisk) {
        this.id = id;
        this.edrpou = edrpou;
        this.name = name;
        this.sum = sum;
        this.documents = documents;
        this.status = status;
        this.manager = manager;
        this.riskmanager = riskmanager;
        this.datetime = datetime;
        this.datetimerisk = datetimerisk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEdrpou() {
        return edrpou;
    }

    public void setEdrpou(String edrpou) {
        this.edrpou = edrpou;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getRiskmanager() {
        return riskmanager;
    }

    public void setRiskmanager(String riskmanager) {
        this.riskmanager = riskmanager;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public Timestamp getDatetimerisk() {
        return datetimerisk;
    }

    public void setDatetimerisk(Timestamp datetimerisk) {
        this.datetimerisk = datetimerisk;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", edrpou='" + edrpou + '\'' +
                ", name='" + name + '\'' +
                ", sum='" + sum + '\'' +
                ", documents='" + documents + '\'' +
                ", status='" + status + '\'' +
                ", manager='" + manager + '\'' +
                ", riskmanager='" + riskmanager + '\'' +
                ", datetime=" + datetime +
                ", datetimerisk=" + datetimerisk +
                '}';
    }
}