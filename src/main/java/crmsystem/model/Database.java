package crmsystem.model;

import crmsystem.dto.*;

import javax.persistence.*;
import java.sql.Timestamp;

@NamedNativeQueries({
@NamedNativeQuery(name = "Clients", query = "SELECT [PR],[client_name],[client_name_short],[full_address], isnull([employee_num],0) as [employee_num]," +
        "[kved],[kved_name],[tender_num],[tender_sum],isnull([revenue_uah],0) AS [revenue_uah],isnull([Forex_per_year_usd],0) AS [Forex_per_year_usd],isnull([loan_value_uah],0) AS [loan_value_uah],isnull([passive_value_uah],0) AS [passive_value_uah]," +
        "[person_details],CONVERT(VARCHAR(10), [last_date_contact], 104) AS [last_date_contact],[last_client_result],[client_history],[status],[comments],[login] " +
        "FROM [crmsystem].[dbo].[GetData1]", resultSetMapping = "Clients"),
@NamedNativeQuery(name = "ClientsByLogin", query = "SELECT [PR],[client_name],[client_name_short],[full_address], isnull([employee_num],0) as [employee_num]," +
        "[kved],[kved_name],[tender_num],[tender_sum],isnull([revenue_uah],0) AS [revenue_uah],isnull([Forex_per_year_usd],0) AS [Forex_per_year_usd],isnull([loan_value_uah],0) AS [loan_value_uah],isnull([passive_value_uah],0) AS [passive_value_uah]," +
        "[person_details],CONVERT(VARCHAR(10), [last_date_contact], 104) AS [last_date_contact],[last_client_result],[client_history],[status],[comments],[login] " +
        "FROM [crmsystem].[dbo].[GetData1] " +
        "WHERE [login] = ?", resultSetMapping = "Clients"),
@NamedNativeQuery(name = "ClientsByOkpoForUpdate", query = "SELECT [PR],[client_name],[client_name_short],[full_address], isnull([employee_num],0) as [employee_num]," +
                "[kved],[kved_name],[tender_num],[tender_sum],isnull([revenue_uah],0) AS [revenue_uah],isnull([Forex_per_year_usd],0) AS [Forex_per_year_usd],isnull([loan_value_uah],0) AS [loan_value_uah],isnull([passive_value_uah],0) AS [passive_value_uah]," +
                "[person_details],CONVERT(VARCHAR(10), [last_date_contact], 23) AS [last_date_contact],[last_client_result],[client_history],[status],[comments],[login] " +
                "FROM [crmsystem].[dbo].[GetData1] " +
                "WHERE [PR] = ?", resultSetMapping = "Clients"),

@NamedNativeQuery(name = "Commisdata", query = "SELECT [client], [group],[EDRPO],[cust],Convert(varchar,cast(convert(datetime, CONVERT(int,[cust open date]),101) as date),104) AS [custOpenDate],[branch],[RM], " +
        "isnull(round([▲% марж дох на акт],1),0) AS [interestMarginAssets], " +
        "isnull(round([▲% марж дох на пас],1),0) AS [interestMarginLiabilities], " +
        "isnull(round([▲коміс дохід],1),0) AS [commisionIncome], " +
        //"CAST(isnull(REPLACE(REPLACE(CONVERT(varchar,CAST([▲коміс дохід] AS money),1),',',' '),'.',','),0) as varchar) AS [commisionIncome], " +
        "isnull([▲в тч коміс по док інстр],0) AS [thereofDocInstruments], " +
        "isnull([▲в тч кред коміс],0) AS [thereofLoanCommisions], " +
        "isnull([▲коміс від куп-прод вал],0) AS [thereofFXCommision], " +
        "isnull([▲інші коміс (РКО, інші…)],0) AS [thereofOtherCommisions], " +
        "isnull([▲заг дохід],0) AS [totalIncome], " +
        "isnull([▲дохід казнач],0) AS [treasuryIncome], " +
        "isnull([▲середден розмір актив], 0) AS [avAssets], " +
        "isnull([▲середден розмір пасив],0) AS [avLiabilities], " +
        "isnull([▲заг дох акт (ROA)],0) AS [ROA] " +
        "FROM [crmsystem].[dbo].[comissdata]", resultSetMapping = "Commisdata"),
@NamedNativeQuery(name = "CommisdataByLogin", query = "SELECT [client], [group],[EDRPO],[cust],Convert(varchar,cast(convert(datetime, CONVERT(int,[cust open date]),101) as date),104) AS [custOpenDate],[branch],[RM], " +
        "isnull([▲% марж дох на акт],0) AS [interestMarginAssets], " +
        "isnull([▲% марж дох на пас],0) AS [interestMarginLiabilities], " +
        "isnull([▲коміс дохід],0) AS [commisionIncome], " +
        //"CAST(isnull(REPLACE(REPLACE(CONVERT(varchar,CAST([▲коміс дохід] AS money),1),',',' '),'.',','),0) as varchar) AS [commisionIncome], " +
        "isnull([▲в тч коміс по док інстр],0) AS [thereofDocInstruments], " +
        "isnull([▲в тч кред коміс],0) AS [thereofLoanCommisions], " +
        "isnull([▲коміс від куп-прод вал],0) AS [thereofFXCommision], " +
        "isnull([▲інші коміс (РКО, інші…)],0) AS [thereofOtherCommisions], " +
        "isnull([▲заг дохід],0) AS [totalIncome], " +
        "isnull([▲дохід казнач],0) AS [treasuryIncome], " +
        "isnull([▲середден розмір актив], 0) AS [avAssets], " +
        "isnull([▲середден розмір пасив],0) AS [avLiabilities], " +
        "isnull([▲заг дох акт (ROA)],0) AS [ROA] " +
        "FROM [crmsystem].[dbo].[comissdata]" +
        "WHERE [ManageLogin] = ?", resultSetMapping = "Commisdata"),

@NamedNativeQuery(name = "InactiveClients", query = "SELECT [Клиент] as [client],[ЕГРПОУ] as [edrpo],[Код контрагента] as [clientcode],[ТТ] as [tt],[Сегмент] as [segment],[Менеджер] as [manager],isnull([ср дн текущ средства (гривна)],0) AS [avgdaycurrdepouah]," +
                "isnull([ср дн сроч средства (гривна)],0) AS [avgdaytermdepouah],isnull([ср дн текущ средства (др валюты)],0) AS [avgdaycurrdepoforeign], " +
                "isnull([ср дн сроч средства (др валюты)],0) AS [avgdaytermdepoforeign],isnull([ср дн текущ средства (мультивалюта)],0) AS [avgdaycurrdepomult]," +
                "isnull([ср дн сроч средства (мультивалюта)],0) AS [avgdaytermdepomult],isnull([Расчетное обслуживание],0) AS [calcservice],isnull([Кассовые операции],0) AS [kasa]," +
                "isnull([ЗКП],0) AS [zkp],isnull([Покупка/продажа валюты],0) AS [buysellforeingcurency],isnull([Ком расходы за перечисление валюты клиентами],0) AS [forexcommision],isnull([КПК],0) AS [kpk], " +
                "isnull([документар- ные операции],0) AS [documentoperation],isnull([Проценты по кредитам],0) AS [loanpercents],isnull([Комиссии по кредитам],0) AS [loancommision],isnull([операции с ЦБ],0) AS [securitiescommision]," +
                "isnull([ИТОГО КТМ],0) AS [totalktm],[dates],CONVERT(VARCHAR(10), [entrydate], 104) AS [entrydate]  FROM [crmsystem].[dbo].[DATABASEInactive]", resultSetMapping = "ClientsInactive"),
@NamedNativeQuery(name = "InactiveClientsByLogin", query = "SELECT [Клиент] as [client],[ЕГРПОУ] as [edrpo],[Код контрагента] as [clientcode],[ТТ] as [tt],[Сегмент] as [segment],[Менеджер] as [manager],isnull([ср дн текущ средства (гривна)],0) AS [avgdaycurrdepouah]," +
                "isnull([ср дн сроч средства (гривна)],0) AS [avgdaytermdepouah],isnull([ср дн текущ средства (др валюты)],0) AS [avgdaycurrdepoforeign], " +
                "isnull([ср дн сроч средства (др валюты)],0) AS [avgdaytermdepoforeign],isnull([ср дн текущ средства (мультивалюта)],0) AS [avgdaycurrdepomult]," +
                "isnull([ср дн сроч средства (мультивалюта)],0) AS [avgdaytermdepomult],isnull([Расчетное обслуживание],0) AS [calcservice],isnull([Кассовые операции],0) AS [kasa]," +
                "isnull([ЗКП],0) AS [zkp],isnull([Покупка/продажа валюты],0) AS [buysellforeingcurency],isnull([Ком расходы за перечисление валюты клиентами],0) AS [forexcommision],isnull([КПК],0) AS [kpk], " +
                "isnull([документар- ные операции],0) AS [documentoperation],isnull([Проценты по кредитам],0) AS [loanpercents],isnull([Комиссии по кредитам],0) AS [loancommision],isnull([операции с ЦБ],0) AS [securitiescommision]," +
                "isnull([ИТОГО КТМ],0) AS [totalktm],[dates],CONVERT(VARCHAR(10), [entrydate], 104) AS [entrydate] FROM [crmsystem].[dbo].[DATABASEInactive] inner join [dbo].[HB_MANAGERS] on [Менеджер] = [ManageReportName] where ManageLogin = ?", resultSetMapping = "ClientsInactive"),

@NamedNativeQuery(name = "ManagerCommisData", query = "exec [dbo].[managerResults] ?", resultSetMapping = "ManagerCommisData"),
@NamedNativeQuery(name = "Managers", query = "SELECT [ManageReportName] AS manager FROM [crmsystem].[dbo].[HB_MANAGERS] ORDER BY 1", resultSetMapping = "Managers"),
@NamedNativeQuery(name = "BranchCommisData", query = "exec [dbo].[branchResults] ?", resultSetMapping = "BranchCommisData"),
@NamedNativeQuery(name = "ManagerDataForPieChart", query = "SELECT [status] as x, count([status]) as value FROM [crmsystem].[dbo].[GetData1] inner join [crmsystem].[dbo].[HB_MANAGERS] on [login] = [ManageLogin]  where [ManageReportName] = ? group by [status]", resultSetMapping = "ManagerDataForPieChart"),
@NamedNativeQuery(name = "ClientsByManager", query = "SELECT [PR],[client_name],[client_name_short],[full_address], isnull([employee_num],0) as [employee_num], [kved],[kved_name],[tender_num],[tender_sum],isnull([revenue_uah],0) AS [revenue_uah],isnull([Forex_per_year_usd],0) AS [Forex_per_year_usd],isnull([loan_value_uah],0) AS [loan_value_uah],isnull([passive_value_uah],0) AS [passive_value_uah], [person_details],cast([last_date_contact] as nvarchar(30)) AS [last_date_contact],[last_client_result],[client_history],[status],[comments],[login] \n" +
        "FROM [crmsystem].[dbo].[GetData1] inner join [crmsystem].[dbo].[HB_MANAGERS] on [login] = [ManageLogin]  where [ManageReportName] = ?", resultSetMapping = "Clients")
})

@SqlResultSetMapping(name = "Clients", classes = @ConstructorResult(targetClass = Clients.class,
        columns = {
                @ColumnResult(name = "PR"),
                @ColumnResult(name = "client_name"),
                @ColumnResult(name = "client_name_short"),
                @ColumnResult(name = "full_address"),
                @ColumnResult(name = "employee_num", type = int.class),
                @ColumnResult(name = "kved"),
                @ColumnResult(name = "kved_name"),
                @ColumnResult(name = "tender_num", type = int.class),
                @ColumnResult(name = "tender_sum", type = int.class),
                @ColumnResult(name = "revenue_uah", type = double.class),
                @ColumnResult(name = "forex_per_year_usd", type = double.class),
                @ColumnResult(name = "loan_value_uah", type = double.class),
                @ColumnResult(name = "passive_value_uah", type = double.class),
                @ColumnResult(name = "person_details"),
                @ColumnResult(name = "last_date_contact"),
                @ColumnResult(name = "last_client_result"),
                @ColumnResult(name = "client_history"),
                @ColumnResult(name = "status"),
                @ColumnResult(name = "comments"),
                @ColumnResult(name = "login")
        }
)
)

@SqlResultSetMapping(name = "Commisdata", classes = @ConstructorResult(targetClass = Commisdata.class,
        columns = {
                @ColumnResult(name = "client"),
                @ColumnResult(name = "group"),
                @ColumnResult(name = "EDRPO"),
                @ColumnResult(name = "cust"),
                @ColumnResult(name = "custOpenDate"),
                @ColumnResult(name = "branch"),
                @ColumnResult(name = "RM"),
                @ColumnResult(name = "interestMarginAssets", type = double.class),
                @ColumnResult(name = "interestMarginLiabilities", type = double.class),
                @ColumnResult(name = "commisionIncome", type = double.class),
                @ColumnResult(name = "thereofDocInstruments", type = double.class),
                @ColumnResult(name = "thereofLoanCommisions", type = double.class),
                @ColumnResult(name = "thereofFXCommision", type = double.class),
                @ColumnResult(name = "thereofOtherCommisions", type = double.class),
                @ColumnResult(name = "totalIncome", type = double.class),
                @ColumnResult(name = "treasuryIncome", type = double.class),
                @ColumnResult(name = "avAssets", type = double.class),
                @ColumnResult(name = "avLiabilities", type = double.class),
                @ColumnResult(name = "ROA", type = double.class),
        }
)
)
@SqlResultSetMapping(name = "ManagerCommisData", classes = @ConstructorResult(targetClass = ManagerCommisData.class,
        columns = {
                @ColumnResult(name = "rm"),
                @ColumnResult(name = "sort", type = int.class),
                @ColumnResult(name = "featureukr"),
                @ColumnResult(name = "averageperyear", type = double.class),
                @ColumnResult(name = "currmonth", type = double.class),
                @ColumnResult(name = "prevmonth", type = double.class),
                @ColumnResult(name = "delta", type = double.class)
        }
)
)
@SqlResultSetMapping(name = "Managers", classes = @ConstructorResult(targetClass = Managers.class,
        columns = {
                @ColumnResult(name = "manager")
        }
)
)
@SqlResultSetMapping(name = "BranchCommisData", classes = @ConstructorResult(targetClass = BranchCommisData.class,
        columns = {
                @ColumnResult(name = "branch"),
                @ColumnResult(name = "sort", type = int.class),
                @ColumnResult(name = "featureukr"),
                @ColumnResult(name = "averageperyear", type = double.class),
                @ColumnResult(name = "currmonth", type = double.class),
                @ColumnResult(name = "prevmonth", type = double.class),
                @ColumnResult(name = "delta", type = double.class)
        }
)
)
@SqlResultSetMapping(name = "ManagerDataForPieChart", classes = @ConstructorResult(targetClass = Status.class,
        columns = {
                @ColumnResult(name = "x"),
                @ColumnResult(name = "value")
        }
)
)

@SqlResultSetMapping(name = "ClientsInactive", classes = @ConstructorResult(targetClass = ClientsInactive.class,
        columns = {
                @ColumnResult(name = "client"),
                @ColumnResult(name = "edrpo"),
                @ColumnResult(name = "clientcode"),
                @ColumnResult(name = "tt"),
                @ColumnResult(name = "segment"),
                @ColumnResult(name = "manager"),
                @ColumnResult(name = "avgdaycurrdepouah",type = int.class),
                @ColumnResult(name = "avgdaytermdepouah", type = int.class),
                @ColumnResult(name = "avgdaycurrdepoforeign", type = int.class),
                @ColumnResult(name = "avgdaytermdepoforeign", type = int.class),
                @ColumnResult(name = "avgdaycurrdepomult", type = int.class),
                @ColumnResult(name = "avgdaytermdepomult", type = int.class),
                @ColumnResult(name = "calcservice", type = int.class),
                @ColumnResult(name = "kasa", type = int.class),
                @ColumnResult(name = "zkp", type = int.class),
                @ColumnResult(name = "buysellforeingcurency", type = int.class),
                @ColumnResult(name = "forexcommision", type = int.class),
                @ColumnResult(name = "kpk", type = int.class),
                @ColumnResult(name = "documentoperation", type = int.class),
                @ColumnResult(name = "loanpercents", type = int.class),
                @ColumnResult(name = "loancommision", type = int.class),
                @ColumnResult(name = "securitiescommision", type = int.class),
                @ColumnResult(name = "totalktm", type = double.class),
                @ColumnResult(name = "dates", type = int.class),
                @ColumnResult(name = "entrydate")
        }
)
)

@Entity
@Table(name = "[DATABASE]")
public class Database {

    @Id
    @Column(name="PR", columnDefinition = "nvarchar(8)")
    String pr;
    @Column(name="PR_ZERO", columnDefinition = "nvarchar(10)")
    String prZero;
    @Column(name="God", columnDefinition = "smallint")
    short god;
    @Column(name="Kvartal", columnDefinition = "tinyint")
    short kvartal;
    @Column(name="OKPO", columnDefinition = "int")
    int okpo;
    @Column(name="KOBL", columnDefinition = "tinyint")
    short kobl;
    @Column(name="KOTU", columnDefinition = "nvarchar(10)")
    String kotu;
    @Column(name="FEL10", columnDefinition = "nvarchar(5)")
    String fel10;
    @Column(name="client_name",columnDefinition = "nvarchar(255)")
    String clientName;
    @Column(name="client_name_short",columnDefinition = "nvarchar(255)")
    String clientNameShort;
    @Column(name="full_address", columnDefinition = "nvarchar(255)")
    String fullAddress;
    @Column(name="phone", columnDefinition = "nvarchar(20)")
    String phone;
    @Column(name="employee_num", columnDefinition = "int")
    int employeeNum;
    @Column(name="OPF", columnDefinition = "nvarchar(3)")
    String opf;
    @Column(name="KKICE", columnDefinition = "smallint")
    short kkice;
    @Column(name="SEK", columnDefinition = "nvarchar(1)")
    String sek;
    @Column(name="Level02", columnDefinition = "nvarchar(2)")
    String level02;
    @Column(name="kved", columnDefinition = "nvarchar(5)")
    String kved;
    @Column(name="kved_name", columnDefinition = "nvarchar(255)")
    String kvedName;
    @Column(name="tender_num", columnDefinition = "int")
    int tenderNum;
    @Column(name="tender_sum", columnDefinition = "int")
    int tenderSum;
    @Column(name="revenue_uah", columnDefinition = "float")
    double revenueUah;
    @Column(name="Forex_per_year_usd", columnDefinition = "float")
    double forexPerYearUsd;
    @Column(name="loan_value_uah", columnDefinition = "float")
    double loanValueUah;
    @Column(name="passive_value_uah", columnDefinition = "float")
    double passiveValueUah;
    @Column(name="person_details", columnDefinition = "nvarchar(255)")
    String personDetails;
    @Column(name="last_date_contact", columnDefinition = "datetime")
    Timestamp lastDateContact;
    @Column(name="last_client_result", columnDefinition = "nvarchar(max)")
    String lastClientResult;
    @Column(name="client_history", columnDefinition = "nvarchar(max)")
    String clientHistory;
    @Column(name="status", columnDefinition = "nvarchar(255) DEFAULT 'не був оброблений'")
    String status;
    @Column(name="importance", columnDefinition = "nvarchar(25)")
    String importance;
    @Column(name="comments", columnDefinition = "nvarchar(max)")
    String comments;
    @Column(name="login", columnDefinition = "nvarchar(255)")
    String login;
/*    @Column(name="entry_date", columnDefinition = "datetime default CURRENT_TIMESTAMP")
    Timestamp entryDate;*/

    public Database() {
    }

    public Database(String pr, String prZero, short god, short kvartal, int okpo, short kobl, String kotu, String fel10, String clientName, String clientNameShort, String fullAddress, String phone, int employeeNum, String opf, short kkice, String sek, String level02, String kved, String kvedName, int tenderNum, int tenderSum, double revenueUah, double forexPerYearUsd, double loanValueUah, double passiveValueUah, String personDetails, Timestamp lastDateContact, String lastClientResult, String clientHistory, String status, String importance, String comments, String login) {
        this.pr = pr;
        this.prZero = prZero;
        this.god = god;
        this.kvartal = kvartal;
        this.okpo = okpo;
        this.kobl = kobl;
        this.kotu = kotu;
        this.fel10 = fel10;
        this.clientName = clientName;
        this.clientNameShort = clientNameShort;
        this.fullAddress = fullAddress;
        this.phone = phone;
        this.employeeNum = employeeNum;
        this.opf = opf;
        this.kkice = kkice;
        this.sek = sek;
        this.level02 = level02;
        this.kved = kved;
        this.kvedName = kvedName;
        this.tenderNum = tenderNum;
        this.tenderSum = tenderSum;
        this.revenueUah = revenueUah;
        this.forexPerYearUsd = forexPerYearUsd;
        this.loanValueUah = loanValueUah;
        this.passiveValueUah = passiveValueUah;
        this.personDetails = personDetails;
        this.lastDateContact = lastDateContact;
        this.lastClientResult = lastClientResult;
        this.clientHistory = clientHistory;
        this.status = status;
        this.importance = importance;
        this.comments = comments;
        this.login = login;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public String getPrZero() {
        return prZero;
    }

    public void setPrZero(String prZero) {
        this.prZero = prZero;
    }

    public short getGod() {
        return god;
    }

    public void setGod(short god) {
        this.god = god;
    }

    public short getKvartal() {
        return kvartal;
    }

    public void setKvartal(short kvartal) {
        this.kvartal = kvartal;
    }

    public int getOkpo() {
        return okpo;
    }

    public void setOkpo(int okpo) {
        this.okpo = okpo;
    }

    public short getKobl() {
        return kobl;
    }

    public void setKobl(short kobl) {
        this.kobl = kobl;
    }

    public String getKotu() {
        return kotu;
    }

    public void setKotu(String kotu) {
        this.kotu = kotu;
    }

    public String getFel10() {
        return fel10;
    }

    public void setFel10(String fel10) {
        this.fel10 = fel10;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientNameShort() {
        return clientNameShort;
    }

    public void setClientNameShort(String clientNameShort) {
        this.clientNameShort = clientNameShort;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(int employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getOpf() {
        return opf;
    }

    public void setOpf(String opf) {
        this.opf = opf;
    }

    public short getKkice() {
        return kkice;
    }

    public void setKkice(short kkice) {
        this.kkice = kkice;
    }

    public String getSek() {
        return sek;
    }

    public void setSek(String sek) {
        this.sek = sek;
    }

    public String getLevel02() {
        return level02;
    }

    public void setLevel02(String level02) {
        this.level02 = level02;
    }

    public String getKved() {
        return kved;
    }

    public void setKved(String kved) {
        this.kved = kved;
    }

    public String getKvedName() {
        return kvedName;
    }

    public void setKvedName(String kvedName) {
        this.kvedName = kvedName;
    }

    public int getTenderNum() {
        return tenderNum;
    }

    public void setTenderNum(int tenderNum) {
        this.tenderNum = tenderNum;
    }

    public int getTenderSum() {
        return tenderSum;
    }

    public void setTenderSum(int tenderSum) {
        this.tenderSum = tenderSum;
    }

    public double getRevenueUah() {
        return revenueUah;
    }

    public void setRevenueUah(double revenueUah) {
        this.revenueUah = revenueUah;
    }

    public double getForexPerYearUsd() {
        return forexPerYearUsd;
    }

    public void setForexPerYearUsd(double forexPerYearUsd) {
        this.forexPerYearUsd = forexPerYearUsd;
    }

    public double getLoanValueUah() {
        return loanValueUah;
    }

    public void setLoanValueUah(double loanValueUah) {
        this.loanValueUah = loanValueUah;
    }

    public double getPassiveValueUah() {
        return passiveValueUah;
    }

    public void setPassiveValueUah(double passiveValueUah) {
        this.passiveValueUah = passiveValueUah;
    }

    public String getPersonDetails() {
        return personDetails;
    }

    public void setPersonDetails(String personDetails) {
        this.personDetails = personDetails;
    }

    public Timestamp getLastDateContact() {
        return lastDateContact;
    }

    public void setLastDateContact(Timestamp lastDateContact) {
        this.lastDateContact = lastDateContact;
    }

    public String getLastClientResult() {
        return lastClientResult;
    }

    public void setLastClientResult(String lastClientResult) {
        this.lastClientResult = lastClientResult;
    }

    public String getClientHistory() {
        return clientHistory;
    }

    public void setClientHistory(String clientHistory) {
        this.clientHistory = clientHistory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "Database{" +
                "pr='" + pr + '\'' +
                ", prZero='" + prZero + '\'' +
                ", god=" + god +
                ", kvartal=" + kvartal +
                ", okpo=" + okpo +
                ", kobl=" + kobl +
                ", kotu='" + kotu + '\'' +
                ", fel10='" + fel10 + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientNameShort='" + clientNameShort + '\'' +
                ", fullAddress='" + fullAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", employeeNum=" + employeeNum +
                ", opf='" + opf + '\'' +
                ", kkice=" + kkice +
                ", sek='" + sek + '\'' +
                ", level02='" + level02 + '\'' +
                ", kved='" + kved + '\'' +
                ", kvedName='" + kvedName + '\'' +
                ", tenderNum=" + tenderNum +
                ", tenderSum=" + tenderSum +
                ", revenueUah=" + revenueUah +
                ", forexPerYearUsd=" + forexPerYearUsd +
                ", loanValueUah=" + loanValueUah +
                ", passiveValueUah=" + passiveValueUah +
                ", personDetails='" + personDetails + '\'' +
                ", lastDateContact=" + lastDateContact +
                ", lastClientResult='" + lastClientResult + '\'' +
                ", clientHistory='" + clientHistory + '\'' +
                ", status='" + status + '\'' +
                ", importance='" + importance + '\'' +
                ", comments='" + comments + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
