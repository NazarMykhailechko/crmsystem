package crmsystem.controller;


import com.google.gson.Gson;
import crmsystem.dao.DatabaseDao;
import crmsystem.dao.LoanDao;
import crmsystem.dto.Clients;
import crmsystem.model.BankBranch;
import crmsystem.model.Database;
import crmsystem.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class CrmSystemController {

    @Autowired
    DatabaseDao databaseDao;
    @Autowired
    LoanDao loanDao;
    @PersistenceContext
    private EntityManager em;

/*    @RequestMapping("/add")
    public String showNewClientPage(Model model) {
        BankBranchCount bankBranchCount = new BankBranchCount();
        model.addAttribute("voting", bankBranchCount);
        return "new_client";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveClient(@ModelAttribute("voting") BankBranchCount voting) {
        branchDao.save(voting);
        System.out.println(voting);
        return "redirect:/";
    }*/

    @RequestMapping("/mainform")
    public String home(Model model, Principal principal, Authentication authentication, HttpServletRequest request) throws IOException {


            boolean hasUserRole = authentication.getAuthorities().stream()
                    .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

            if (hasUserRole){

                Query q1 = em.createNativeQuery("SELECT [client]\n" +
                        "      ,[group]\n" +
                        "      ,[EDRPO]\n" +
                        "      ,[cust]\n" +
                        "      ,[cust open date]\n" +
                        "      ,[branch]\n" +
                        "      ,[RM]\n" +
                        "      ,[▲% марж дох на акт]\n" +
                        "      ,[▲% марж дох на пас]\n" +
                        "      ,[▲коміс дохід]\n" +
                        "      ,[▲в тч коміс по док інстр]\n" +
                        "      ,[▲в тч кред коміс]\n" +
                        "      ,[▲коміс від куп-прод вал]\n" +
                        "      ,[▲інші коміс (РКО, інші…)]\n" +
                        "      ,[▲заг дохід]\n" +
                        "      ,[▲дохід казнач]\n" +
                        "      ,[▲середден розмір актив]\n" +
                        "      ,[▲середден розмір пасив]\n" +
                        "      ,[▲заг дох акт (ROA)]\n" +
                        "  FROM [comissdata]\n");
                List<Object[]> results1 = q1.getResultList();

                model.addAttribute("commisdata", results1);

            }else{

                Query q1 = em.createNativeQuery("SELECT [client]\n" +
                        "      ,[group]\n" +
                        "      ,[EDRPO]\n" +
                        "      ,[cust]\n" +
                        "      ,[cust open date]\n" +
                        "      ,[branch]\n" +
                        "      ,[RM]\n" +
                        "      ,[▲% марж дох на акт]\n" +
                        "      ,[▲% марж дох на пас]\n" +
                        "      ,[▲коміс дохід]\n" +
                        "      ,[▲в тч коміс по док інстр]\n" +
                        "      ,[▲в тч кред коміс]\n" +
                        "      ,[▲коміс від куп-прод вал]\n" +
                        "      ,[▲інші коміс (РКО, інші…)]\n" +
                        "      ,[▲заг дохід]\n" +
                        "      ,[▲дохід казнач]\n" +
                        "      ,[▲середден розмір актив]\n" +
                        "      ,[▲середден розмір пасив]\n" +
                        "      ,[▲заг дох акт (ROA)]\n" +
                        "  FROM [comissdata]\n" +
                        "  where [ManageLogin] = ?");
                q1.setParameter(1,principal.getName());
                List<Object[]> results1 = q1.getResultList();

                model.addAttribute("commisdata", results1);

            }

            return "mainform";
    }

    @RequestMapping("/mainform/updateclient/{okpo}")
    public ModelAndView updateMyClient(@PathVariable(name = "okpo") String id) {
        ModelAndView mav = new ModelAndView("updateclient");
        Clients database = em.createNamedQuery("ClientsByOkpoForUpdate",Clients.class).setParameter(1,id).getSingleResult();
        //Database database = databaseDao.findById(id).get();
        mav.addObject("database", database);
        return mav;
    }

    @RequestMapping("/mainform/edrpou/{edrpou}/cust/{cust}")
    public String getCommissDetails(Model model, @PathVariable(name = "edrpou") String edrpou, @PathVariable(name = "cust") String cust) {

        Query fieldnames = em.createNativeQuery("SELECT distinct [dates] FROM [DATABASEcomision] order by [dates] desc");
        List<Object[]> listOfFields = fieldnames.getResultList();

        Query q = em.createNativeQuery("EXEC [dbo].[crossdata] ?, ?");
        q.setParameter(1,edrpou);
        q.setParameter(2,cust);

        List<Object[]> results = q.getResultList();

        model.addAttribute("listOfVotes", results);
        model.addAttribute("listOfFields", listOfFields);

        return "comisdetails";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String saveUpdateClient(@RequestParam("pr") String id,
                                   @RequestParam("last_date_contact") String lastDateContact,
                                   @RequestParam("last_client_result") String lastClientResult,
                                   @RequestParam("client_history") String clientHistory,
                                   @RequestParam("status") String status,
                                   @RequestParam("comments") String comments) throws ParseException {

        //String userlogin = System.getenv("username");
        Database database = databaseDao.findById(id).get();
        if (!lastDateContact.equals("")){
            database.setLastDateContact(Timestamp.valueOf(lastDateContact.concat(" 00:00:00")));
        }else{
            database.setLastDateContact(null);
        }

        if (!lastClientResult.equals("")){
            database.setLastClientResult(lastClientResult);
        }else{
            database.setLastClientResult(null);
        }

        database.setStatus(status);
        database.setComments(comments);
        database.setClientHistory(clientHistory);
        //database.setFullAddress(full_address);
        databaseDao.save(database);
        return "mainform";
    }

    @RequestMapping("/results")
    public ModelAndView results(Model model) throws IOException {
     BankBranch bankBranch = new BankBranch();
        ModelAndView mv = new ModelAndView("bankBranch");
        mv.addObject("bankBranch", bankBranch);

        URL url = new URL("https://bank.gov.ua/NBU_BankInfo/get_data_branch?glmfo=380634&json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.ISO_8859_1));

        BankBranch[] dto = new Gson().fromJson(reader, BankBranch[].class);

        List<BankBranch> bankBranchList = Arrays.asList(dto);
        for (BankBranch branch : bankBranchList) {
            System.out.println(branch.getShortname());
        }

        /*Map<String, Long> counting = bankBranchList.stream().filter(b -> b.getDepcode().startsWith("0", 8)
                || b.getDepcode().startsWith("1", 8)
                || b.getDepcode().startsWith("2", 8)).collect(
                Collectors.groupingBy(BankBranch::getGLB, Collectors.counting()));

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Long> entry : counting.entrySet()) {
            sb.append("{'glb':'").append(entry.getKey()).append("','count'").append(":").append(entry.getValue()).append("},");
            //System.out.println("{" + entry.getKey() + ":" + entry.getValue());
        }

        String JSONdata = "[" + sb.substring(0, sb.length() - 1) + "]";

        //System.out.println(JSONdata);

        BankBranchCount[] bbc = new Gson().fromJson(JSONdata, BankBranchCount[].class);
        branchDao.saveAll((Arrays.asList(bbc)));

        for (BankBranchCount vt : branchDao.findAll()) {
            System.out.print(vt.toString());
        }*/
        mv.addObject("bankBranchList", bankBranchList);
        //model.addAttribute("bankBranchList", bankBranchList);
        return mv;
    }

    @RequestMapping("/managersreports")
    public String data(Model model) {
        return "managersreports";
    }


    @RequestMapping("/risk")
    public String risk(Model model) {
        //model.addAttribute("listOfLoans", loanDao.findAll());
        return "risk";
    }

    @RequestMapping("/updateloanrisks/{id}")
    public ModelAndView updateClientRisks(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("updateloanrisks");
        Loan loan = loanDao.findById(id).get();
        mav.addObject("loan", loan);
        return mav;
    }

    @RequestMapping(value = "/updaterisks", method = RequestMethod.POST)
    public String saveUpdateClientRisks(@RequestParam("id") int id,
                                        @RequestParam("status") String status, Principal principal, Authentication authentication) {
        //String userlogin = System.getenv("username");
        Loan loan = loanDao.findById(id).get();
        loan.setStatus(status);
        loan.setRiskmanager(principal.getName());
        loan.setDatetimerisk(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        loanDao.save(loan);
        return "redirect:/risk";
    }

    @RequestMapping("/corp")
    public String corp(Model model) {
        model.addAttribute("listOfLoans", loanDao.findAll());
        return "corp";
    }

    @RequestMapping("/updateloan/{id}")
    public ModelAndView updateLoan(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("updateloan");
        Loan loan = loanDao.findById(id).get();
        mav.addObject("loan", loan);
        return mav;
    }

    @RequestMapping(value = "/updateloan", method = RequestMethod.POST)
    public String saveUpdateLoan(@RequestParam("id") int id,
                                 @RequestParam("edrpou") String edrpou,
                                 @RequestParam("name") String name,
                                 @RequestParam("sum") String sum,
                                 @RequestParam("documents") String documents, Principal principal, Authentication authentication) {
        //String userlogin = System.getenv("username");
        Loan loan = loanDao.findById(id).get();
        loan.setEdrpou(edrpou);
        loan.setName(name);
        loan.setSum(sum);
        loan.setDocuments(documents);
        loan.setManager(principal.getName());
        loan.setDatetime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        loanDao.save(loan);
        return "redirect:/corp";
    }

    @RequestMapping(value ="/addloan")
    public String showNewClientPage(Model model) {
        Loan loan = new Loan();
        model.addAttribute("loan", loan);
        return "addloan";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveClient(@ModelAttribute("loan") Loan loan, Principal principal) {
        loan.setManager(principal.getName());
        loan.setDatetime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        loanDao.save(loan);
        return "redirect:/corp";
    }

}
