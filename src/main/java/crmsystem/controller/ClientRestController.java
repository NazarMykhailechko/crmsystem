package crmsystem.controller;

import com.google.gson.Gson;
import crmsystem.dao.DatabaseDao;
import crmsystem.dto.*;
import crmsystem.model.BankBranch;
import crmsystem.model.Database;
import crmsystem.model.Loan;
import org.hibernate.loader.custom.ConstructorResultColumnProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    DatabaseDao databaseDao;


    @RequestMapping(value = "findall", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Clients>> findAll(Principal principal, Authentication authentication) {

        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        List<Clients> res;
        if (hasUserRole) {
            res = em.createNamedQuery("Clients",Clients.class).getResultList();
        }else{
            res = em.createNamedQuery("ClientsByLogin",Clients.class).setParameter(1,principal.getName()).getResultList();
        }

        try {
            return new ResponseEntity<Iterable<Clients>>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<Clients>>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "findallcomissdata", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Commisdata>> findAllComissData(Principal principal, Authentication authentication) {

        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        List<Commisdata> res;
        if (hasUserRole) {
            res = em.createNamedQuery("Commisdata", Commisdata.class).getResultList();
        }else{
            res = em.createNamedQuery("CommisdataByLogin",Commisdata.class).setParameter(1,principal.getName()).getResultList();
        }

        try {
            return new ResponseEntity<Iterable<Commisdata>>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<Commisdata>>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "findallclientsinactive", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<ClientsInactive>> findAllClientsInactive(Principal principal, Authentication authentication) {

        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        List<ClientsInactive> res;
        if (hasUserRole) {
            res = em.createNamedQuery("InactiveClients",ClientsInactive.class).getResultList();
        }else{
            res = em.createNamedQuery("InactiveClientsByLogin",ClientsInactive.class).setParameter(1,principal.getName()).getResultList();
        }

        try {
            return new ResponseEntity<Iterable<ClientsInactive>>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<ClientsInactive>>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "findallbankbranches", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<BankBranch>> findAllBankBranches(Principal principal, Authentication authentication) throws IOException {

            URL url = new URL("https://bank.gov.ua/NBU_BankInfo/get_data_branch?glmfo=380634&typ=2&json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            BankBranch[] dto = new Gson().fromJson(reader, BankBranch[].class);
            List<BankBranch> bankBranchList = Arrays.asList(dto);
            bankBranchList.forEach(n -> n.setShortname(n.getShortname().replace("№ ","№").trim()));
            bankBranchList.sort(Comparator.comparingInt(o -> Integer.parseInt(o.getShortname().substring(o.getShortname().indexOf("№") + 1)
                    .substring(0, o.getShortname()
                            .substring(o.getShortname().indexOf("№") + 1).indexOf(" ")))));

        try {
            return new ResponseEntity<Iterable<BankBranch>>(bankBranchList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<BankBranch>>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "findallbankbrancheswithparam/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<BankBranch>> findAllBankBranchesWithParam(@PathVariable String id) throws IOException {

        List<BankBranch> bankBranchList;
        BufferedReader reader;
        System.out.println(id);
        if (id.equals("Головний Банк")){
            URL url = new URL("https://bank.gov.ua/NBU_BankInfo/get_data_branch?glmfo=380634&typ=0&json");

            reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            BankBranch[] dto = new Gson().fromJson(reader, BankBranch[].class);
            bankBranchList = Arrays.asList(dto);
            bankBranchList.forEach(n -> n.setShortname(n.getShortname().replace("№ ","№").trim()));

        }else{
                String div = id.replace("№ ","№").substring(id.indexOf("№")).substring(0,id.replace("№ ","№").substring(id.indexOf("№")).indexOf(" ")+1);

                URL url = new URL("https://bank.gov.ua/NBU_BankInfo/get_data_branch?glmfo=380634&typ=2&json");
                List<BankBranch> bankBranch;
                reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
                BankBranch[] dto = new Gson().fromJson(reader, BankBranch[].class);
                bankBranchList = new ArrayList<BankBranch>(Arrays.asList(dto));
                bankBranchList.forEach(n -> n.setShortname(n.getShortname().replace("№ ","№").trim()));
                bankBranch = bankBranchList.stream().filter(n -> n.getShortname().contains(div)).collect(Collectors.toList());
                bankBranchList.clear();
                bankBranchList.addAll(bankBranch);
        }

        try {
            return new ResponseEntity<Iterable<BankBranch>>(bankBranchList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<BankBranch>>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "findallmanagercommisdata/{manager}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<ManagerCommisData>> findAllManagerComissData(@PathVariable String manager) {

        List<ManagerCommisData> res = em.createNamedQuery("ManagerCommisData", ManagerCommisData.class).setParameter(1,manager).getResultList();

        try {
            return new ResponseEntity<Iterable<ManagerCommisData>>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<ManagerCommisData>>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "findallmanagers", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Managers>> findAllManagers() {

        List<Managers> res = em.createNamedQuery("Managers", Managers.class).getResultList();

        try {
            return new ResponseEntity<Iterable<Managers>>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<Managers>>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "findallbranchcommisdata/{branch}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<BranchCommisData>> findAllBranchComissData(@PathVariable String branch) {

        if (branch.equals("Головний Банк")){
            branch = "0";
        }else{
            branch = branch.replace("№ ","№").substring(branch.indexOf("№")+1).substring(0,branch.replace("№ ","№").substring(branch.indexOf("№")+1).indexOf(" ")).trim();
        }

        List<BranchCommisData> res = em.createNamedQuery("BranchCommisData", BranchCommisData.class).setParameter(1,branch).getResultList();

        try {
            return new ResponseEntity<Iterable<BranchCommisData>>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<BranchCommisData>>(HttpStatus.BAD_REQUEST);
        }
    }

    /*@RequestMapping(value = "findallmanagerstatusforchart", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Status>> findAllManagerStatusForChart(Principal principal, Authentication authentication) {

        List<Clients> res = em.createNamedQuery("Clients",Clients.class).getResultList();

        Map<String, Long> counting = res.stream().filter(b -> b.getLogin().contains("olanhaieva")).collect(
                Collectors.groupingBy(Clients::getStatus, Collectors.counting()));

        System.out.println(counting);

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Long> entry : counting.entrySet()) {
            sb.append("{'x':'").append(entry.getKey()).append("','value'").append(":").append(entry.getValue()).append("},");
            //System.out.println("{" + entry.getKey() + ":" + entry.getValue());
        }

        String JSONdata = "[" + sb.substring(0, sb.length() - 1) + "]";

        System.out.println(JSONdata);

        Status[] dto = new Gson().fromJson(JSONdata, Status[].class);

        List<Status> statusList = Arrays.asList(dto);

        try {
            return new ResponseEntity<Iterable<Status>>(statusList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<Status>>(HttpStatus.BAD_REQUEST);
        }
    }
*/
    @RequestMapping(value = "findallmanagerstatusforchart/{login}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Status>> findAllManagerStatusForChart(@PathVariable String login) {

        List<Status> res = em.createNamedQuery("ManagerDataForPieChart",Status.class).setParameter(1,login).getResultList();

        try {
            return new ResponseEntity<Iterable<Status>>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<Status>>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "findallmanagerbyname/{manager}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Clients>> findallManagerByName(@PathVariable String manager) {

        List<Clients> res = em.createNamedQuery("ClientsByManager",Clients.class).setParameter(1,manager).getResultList();

        try {
            return new ResponseEntity<Iterable<Clients>>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<Clients>>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "findallloans", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Loan>> findallloans(Principal principal, Authentication authentication) {

        //<Loan> query = em.createQuery("Select l from Loan l", Loan.class);
        //List<Loan> res = query.getResultList();
        //List<Loan> res = em.createQuery("SELECT c FROM LOANS c", Loan.class).getResultList();

        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN") || r.getAuthority().equals("ROLE_RISK"));
        List<Loan> res;
        if (hasUserRole) {
            TypedQuery<Loan> query = em.createQuery("Select l from Loan l", Loan.class);
            res = query.getResultList();
        }else{
            TypedQuery<Loan> query = em.createQuery("Select l from Loan l where l.manager= :manager", Loan.class).setParameter("manager",principal.getName());
            res = query.getResultList();
            //res = em.createNamedQuery("ClientsByLogin",Clients.class).setParameter(1,principal.getName()).getResultList();
        }

        try {
            return new ResponseEntity<Iterable<Loan>>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<Loan>>(HttpStatus.BAD_REQUEST);
        }
    }

}