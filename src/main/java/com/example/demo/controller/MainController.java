/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.About;
import com.example.demo.model.Authorities;
import com.example.demo.model.Blog;
import com.example.demo.model.ContactForm;
import com.example.demo.model.Testimony;
import com.example.demo.model.Donation;
import com.example.demo.model.Image;
import com.example.demo.model.MailForm;
import com.example.demo.model.Members;
import com.example.demo.model.Program;
import com.example.demo.model.Sermon;
import com.example.demo.model.VerificationToken;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.service.AboutService;
import com.example.demo.service.BlogService;
import com.example.demo.service.ContactFormService;
import com.example.demo.service.DonationService;
import com.example.demo.service.EmailService;
import com.example.demo.service.ImageService;
import com.example.demo.service.MailFormService;
import com.example.demo.service.MembersService;
import com.example.demo.service.ProgramService;
import com.example.demo.service.SermonService;
import com.example.demo.service.TestimonyService;
import com.example.demo.service.VerificationTokenService;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author babafemi
 */
@Controller
public class MainController {

    @Value("${files_uploading_directory}")
    public String upload_directory;
 @Value("${reg_message}")
    public String reg_message;

     @Autowired
    private ContactFormService contactFormService;
      @Autowired
    private VerificationTokenService verificationTokenService;
      @Autowired
    private SermonService sermonService;
      @Autowired
    private MailFormService mailFormService;
    @Autowired
    private ImageService imageService;
     @Autowired 
             private TestimonyService testimonyService;
     @Autowired 
             private DonationService donationService;
     @Autowired 
           private MembersService membersService;
     @Autowired
     private AboutService aboutService;
      @Autowired
     private ProgramService programService;
       @Autowired
     private AuthorityRepository authorityRepository;
    
     @Autowired
     private BlogService blogService;
      @Autowired
     private EmailService emailService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("testimonies", testimonyService.getAllAprovedTestimony());
        return "index";
    }
    
    @GetMapping("/index2")
    public String index2(Model model){
        return "index2";
    }

    @GetMapping("/ourmission")
    public String ourmission(Model model){
        model.addAttribute("About", aboutService.getAbout(1L));
        return "ourmission";
    }
    
      @GetMapping("/contact")
    public String contact(Model model){
        model.addAttribute("cf", new ContactForm());
        return "contact";
    }
    @PostMapping("/contact")
    public String saveContact(@ModelAttribute ContactForm cf, Model model){
        cf.setReplied(0);
        cf.setDateAdded(new Date());
  
        contactFormService.addContactForm(cf);
        model.addAttribute("status", true);
    return "redirect:/contact";
    }
       
   @GetMapping("/all_contact")
   public String AllContact(Model model){
   model.addAttribute("allcontact", contactFormService.getAllContactForm());
   return "allcontact";
     
   }
   
   
    
   
   
       @GetMapping("/sermons")
    public String sermons(Model model) {
        model.addAttribute("sermons", sermonService.getAllSermons());
        return "sermons";
}     
       
 @GetMapping("/sermons/add")
    public String addsermon(Model model) {
        model.addAttribute("ser",new Sermon());
        return "addsermon";
}
    
    @GetMapping("sermon/edit/{id}")
    public String addsermon(Model model, @PathVariable Long id) {
        
        model.addAttribute("ser", sermonService.getSermon(id));
        return "addsermon";
        
}
    
    
     @PostMapping("/sermon")
    public String saveSermon(@ModelAttribute Sermon ser, Model model, @RequestParam("sermonImage") MultipartFile sermonImage){ 
     Long l= ser.getId();
        if(l != null) {
            Sermon s= sermonService.getSermon(l);

            ser.setDateAdded(s.getDateAdded());
        ser.setHide(s.getHide());
        
        if(sermonImage.getOriginalFilename().isEmpty()){
      
       
         
        ser.setBImage(s.getBImage());
        
       sermonService.addSermon(ser);
       

          //continue;
    }else{
        
//          ser.setDateAdded(new Date());
//   ser.setHide(1);
        
        String fileName =  System.currentTimeMillis()+"_"+StringUtils.cleanPath(sermonImage.getOriginalFilename());
        Path path = Paths.get(upload_directory + fileName);
        try {
            Files.copy(sermonImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .toUriString();
    
        Image image= new Image(ser.getTitle(),  fileDownloadUri);
        ser.setBImage(image);
       
       
    } 
        sermonService.addSermon(ser);
         return "redirect:/all_sermons";
        }else
        {
            if(sermonImage.getOriginalFilename().isEmpty()){
           
       
          //continue;
    }else{
        
          ser.setDateAdded(new Date());
           ser.setHide(1);
        
        String fileName =  System.currentTimeMillis()+"_"+StringUtils.cleanPath(sermonImage.getOriginalFilename());
        Path path = Paths.get(upload_directory + fileName);
        try {
            Files.copy(sermonImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .toUriString();
    
        Image image= new Image(ser.getTitle(),  fileDownloadUri);
        ser.setBImage(image);
        
       
    }
            sermonService.addSermon(ser);
         return "redirect:/sermons/add";
                  
              
        }
   
        
  
}
    
        
   @GetMapping("/all_sermons")
   public String AllSermons(Model model){
   model.addAttribute("allsermons", sermonService.getAllSermons());
   return "allsermons";
     
   } 

   
   
   @GetMapping("sermon/hide/{id}")
    public String approveSermon(Model model, @PathVariable Long id){
        Sermon b=sermonService.getSermon(id);
        if (b!= null){
        b.setHide(0);
        sermonService.updateSermon(b);
        }
          model.addAttribute("allsermons", sermonService.getAllSermons());
        return "redirect:/all_sermons";
    
       }
  @GetMapping("sermon/unhide/{id}")
    public String unApproveSermon(Model model, @PathVariable Long id){
        Sermon b=sermonService.getSermon(id);
        if (b!= null){
        b.setHide(0);
        sermonService.updateSermon(b);
        }
          model.addAttribute("allsermons", sermonService.getAllSermons());
        return "redirect:/all_sermons";
    
       }
   ;
    
   
    
     @GetMapping("/blogview")
    public String blogview() {
        return "blogview";
}
    
     @GetMapping("/gallery")
    public String gallery(Model model) {
model.addAttribute("images", imageService.getGalleryImages());
        return "photos";
}
     @GetMapping("/testimony")
    public String testimony(Model model) {
        model.addAttribute("testimonies", testimonyService.getAllAprovedTestimony());
        model.addAttribute("tes", new Testimony());
        return "testimony";
}

@GetMapping("/testimony/{id}")
public String view_single_testimony(Model model, @PathVariable int id) {
    Testimony t= testimonyService.getTestimony(Long.valueOf(id));
    if(t!=null){
        if (t.getApproved()==1){
            model.addAttribute("testimony", t);
            return "single_testimony";
        }
    }
    return "redirect:/testimony";
}
    
      @PostMapping("/testimony")
    public String saveTestitony(@ModelAttribute  Testimony tes, Model model){
        //cf.setDateAdded(new Date());
        tes.setDateAdded(new Date());
        tes.setApproved(0);
        testimonyService.addTestimony(tes);
        model.addAttribute("status", true);
    return "redirect:/testimony";
}    
    
     @GetMapping("/AllTestimony")
    public String ViewAllTestimony(Model model) {
        model.addAttribute("allTestimony", testimonyService.getAllTestimony());
        return "allTestimony";
}
           @GetMapping("/unapprovedTestimony")
    public String ViewUapprovedTestimony(Model model) {
        model.addAttribute("unapprovedTestimony", testimonyService.getAllUnAprovedTestimony());
        return "unapprovedTestimony";
}
    @GetMapping("/ApprovedTestimony")
    public String ViewApprovedTestimony(Model model) {
        model.addAttribute("approvedTestimony", testimonyService.getAllAprovedTestimony());
        return "approvedTestimony";
}
    @GetMapping("testimony/approve/{id}")
    public String approveTestimony(Model model, @PathVariable Long id){
        Testimony t=testimonyService.getTestimony(id);
        if (t!= null){
        t.setApproved(1);
        testimonyService.updateTestimony(t);
        }
          model.addAttribute("unapprovedPosts", testimonyService.getAllUnAprovedTestimony());
        return "redirect:/unapprovedTestimony";
    
       }
    @GetMapping("testimony/unapprove/{id}")
    public String unApproveTestmony(Model model, @PathVariable Long id){
        Testimony b=testimonyService.getTestimony(id);
        if (b!= null){
        b.setApproved(0);
        testimonyService.updateTestimony(b);
        }
          model.addAttribute("approvedTestimony", testimonyService.getAllAprovedTestimony());
        return "redirect:/ApprovedTestimony";
    
       }
    @GetMapping("testimony/tes/approve/{id}")
    public String approveTestimonyAll(Model model, @PathVariable Long id){
        Testimony b=testimonyService.getTestimony(id);
        if (b!= null){
        b.setApproved(1);
        testimonyService.updateTestimony(b);
        }
          model.addAttribute("unapprovedTestimony", testimonyService.getAllUnAprovedTestimony());
        return "redirect:/AllTestimony";
    
       }
  @GetMapping("testimony/tes/unapprove/{id}")
    public String unApproveTestimonyAll(Model model, @PathVariable Long id){
        Testimony b=testimonyService.getTestimony(id);
        if (b!= null){
        b.setApproved(0);
        testimonyService.updateTestimony(b);
        }
          model.addAttribute("approvedTestimony", testimonyService.getAllUnAprovedTestimony());
        return "redirect:/AllTestimony";
    
       }
    
    
    
    
     @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("mem", new Members());
        return "register";
}
    
    @PostMapping("/register")
    public String SaveRegister(@ModelAttribute Members mem, Model model, HttpServletRequest request
) {
          if (mem.getPassword().equals(mem.getConfirmPassword())== false ){
           model.addAttribute("error", "passwords do not match") ;
        model.addAttribute("mem", mem);
        
        return "register";
            }
          
        Members newMem= membersService.getMember(mem.getEmail());
        if (newMem != null){
        model.addAttribute("message", "This email already exists");
        return "baduser";
        }
        else{
       mem.setDateRegistered(new Date());
       mem.setUnit("");
       mem.setRoles("");
      // Set<Authorities> autho= new HashSet<>();
     //  autho.add(authorityRepository.findByAuthorityName("ROLE_MEMBER"));
       mem.getAuthority().add(authorityRepository.findByAuthorityName("ROLE_MEMBER"));
      mem= membersService.addMembers(mem);
      VerificationToken vt= new VerificationToken();
      vt.setMember(mem);
      String token = UUID.randomUUID().toString();
      
        vt.setToken(token);
        verificationTokenService.createToken(vt);
       String appUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

      //  String appUrl = request.getContextPath();
        String recipientAddress = mem.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = appUrl + "/register/registrationConfirm?token=" + token;
        String message =reg_message+confirmationUrl;
        emailService.sendMail(recipientAddress, subject, message);

       //  membersService.addMembers(mem);
         model.addAttribute("status", true);
        return "redirect:/register";
}
    } 
    
    @GetMapping("register/registrationConfirm")
public String confirmRegistration
  ( Model model, @RequestParam("token") String token) {
 
    VerificationToken verificationToken = verificationTokenService.getToken(token);
    if (verificationToken == null) {

        model.addAttribute("message", "Invalid Token");
        return "baduser";
    }
    
  Members mem = verificationToken.getMember();
    Calendar cal = Calendar.getInstance();
    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
      
        model.addAttribute("message", "Expired Token");
        return "baduser";
    } 
    
   mem.setVerified(true); 
   membersService.updateMembers(mem); 
    return "verified";
}
    
    
    
    @RequestMapping(value = "/viewmembers", method = RequestMethod.GET)
    public String ViewMembers(Model model) {
        model.addAttribute("allmembers", membersService.getAllMembers());
        return "allmembers";
}
    
     @GetMapping("/login")
    public String login() {
        return "login";
} 
    
    
    
     @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPage(@RequestParam(value = "error", required = false) String error, 
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessge = null;
        if(error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }
        if(logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessge", errorMessge);
         return "redirect:/login";
    }
  
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }
    
    
    
     @GetMapping("/donation")
    public String donation(Model model) {
        model.addAttribute("don",new Donation());
        return "donation";
}

@PostMapping("/donation")
    public String saveDonation(@ModelAttribute Donation don, Model model){
    don.setDateDonated(new Date());
    don.setDonationMethod("Bank Transfer");
    don.setApproved(0);
    donationService.addDonation(don);
    model.addAttribute("status", true);
    
    return "redirect:/donation";
    
    }
    
    @GetMapping("/all_donations")
   public String AllDonations(Model model){
   model.addAttribute("alldonations", donationService.getAllDonation());
   return "alldonations";
     
   }

    @GetMapping("donation/approve/{id}")
    public String approveDonation(Model model, @PathVariable Long id){
        Donation b=donationService.getDonation(id);
        if (b!= null){
        b.setApproved(1);
        donationService.updateDonation(b);
        }
          model.addAttribute("alldonations", donationService.getAllDonation());
        return "redirect:/all_donations";
    
       }
  @GetMapping("donation/unapprove/{id}")
    public String unApproveDonation(Model model, @PathVariable Long id){
        Donation b=donationService.getDonation(id);
        if (b!= null){
        b.setApproved(0);
        donationService.updateDonation(b);
        }
          model.addAttribute("alldonations", donationService.getAllDonation());
        return "redirect:/all_donations";
    
       }
   
   
@GetMapping("/onlinegiving")
public String onlinedonation(Model model) {
    model.addAttribute("don",new Donation());
    return "donation2";
}
    
@PostMapping("/onlinegiving")
    public String saveOnlineDonation(@ModelAttribute Donation don, Model model){
    don.setDateDonated(new Date());
    don.setDonationMethod("PayStack");
    don.setApproved(0);
    don=donationService.addDonation(don);
    model.addAttribute("status", true);
    model.addAttribute("donation", don);
    return "payment_page";
    
    }

     @GetMapping("/about")
    public String about(Model model) {
        About a= aboutService.getAbout(1L);
        model.addAttribute("ab",a);
        return "about";
}
    @PostMapping("/about")
   public String saveAbout(@ModelAttribute About ab, Model model, @RequestParam ("logo") MultipartFile logo ){

    ab.setDateUpdated(new Date());
   ab.setId(1L);
   
    if(logo.getOriginalFilename().isEmpty()){
        //continue;
    }else{
        String fileName =  System.currentTimeMillis()+"_"+StringUtils.cleanPath(logo.getOriginalFilename());
        Path path = Paths.get(upload_directory + fileName);
        try {
            Files.copy(logo.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .toUriString();
    
        Image image= new Image("logo",  fileDownloadUri);
        ab.setLImage(image);
    }

  
   
   aboutService.addAbout(ab);
    model.addAttribute("status", true);
    
    return "redirect:/about";
    
    }
    
     @GetMapping("/program")
    public String program(Model model) {
        model.addAttribute("programs",programService.getAllUpcomingPrgram());
        return "programs";
}
    
   @GetMapping("/program/add")
    public String addProgram(Model model) {
        model.addAttribute("prog", new Program());
        return "addprogram";
}
    @PostMapping("/program")
    public String saveProgram(@ModelAttribute Program prog, Model model, @RequestParam("programImage") MultipartFile programImage){
  
        
         Long l= prog.getId();
        if(l != null) {
            Program s= programService.getProgram(l);

            prog.setDatePosted(s.getDatePosted());
        prog.setUpcoming(s.getUpcoming());
        
        if(programImage.getOriginalFilename().isEmpty()){
      
       
         
        prog.setPImage(s.getPImage());
        
       programService.addProgram(prog);
       

          //continue;
    } else{
            
             String fileName =  System.currentTimeMillis()+"_"+StringUtils.cleanPath(programImage.getOriginalFilename());
        Path path = Paths.get(upload_directory + fileName);
        try {
            Files.copy(programImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .toUriString();
    
        Image image= new Image(prog.getTitle(),  fileDownloadUri);
        prog.setPImage(image);
    }

  
       programService.addProgram(prog);
    model.addAttribute("status", true);
    
    return "redirect:/all_programs";
    
            
            
        }
        
        else{
         prog.setDatePosted(new Date());
   prog.setUpcoming(1);

    if(programImage.getOriginalFilename().isEmpty()){
        //continue;
    }else{
        String fileName =  System.currentTimeMillis()+"_"+StringUtils.cleanPath(programImage.getOriginalFilename());
        Path path = Paths.get(upload_directory + fileName);
        try {
            Files.copy(programImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .toUriString();
    
        Image image= new Image(prog.getTitle(),  fileDownloadUri);
        prog.setPImage(image);
    }

  
       programService.addProgram(prog);
    model.addAttribute("status", true);
    
    return "redirect:/program/add";
    
    }
}
        

        
        
        
        
       
      @GetMapping("program/edit/{id}")
    public String addEditProgram(Model model, @PathVariable Long id) {
        
        model.addAttribute("prog", programService.getProgram(id));
        return "addprogram";
        
}
    
@GetMapping("/all_programs")
 public String ViewAllPrograms(Model model) {
        model.addAttribute("allPrograms", programService.getAllProgram());
        return "allPrograms";
}
  
  @GetMapping("program/past/{id}")
    public String pastProgram(Model model, @PathVariable Long id){
        Program b=programService.getProgram(id);
        if (b!= null){
        b.setUpcoming(0);
        programService.updateProgram(b);
        }
          model.addAttribute("allprograms", programService.getAllProgram());
        return "redirect:/all_programs";
    
       }
  @GetMapping("program/upcoming/{id}")
    public String upcomingProgram(Model model, @PathVariable Long id){
       Program b=programService.getProgram(id);
        if (b!= null){
        b.setUpcoming(1);
        programService.updateProgram(b);
        }
          model.addAttribute("allprograms", programService.getAllProgram());
        return "redirect:/all_programs";
    
       }
   
    
    
    @GetMapping("/blog")
    public String blog(Model model) {
        model.addAttribute("blogs", blogService.getAllAprovedBlog());
        return "blogs";
}


@GetMapping("/gallery/add")
public String addGalleryImage(Model model) {
    model.addAttribute("image",new Image());
    return "upload_gallery";
}
@PostMapping("/gallery")
public String saveGalleryImage(@ModelAttribute Image image, Model model, @RequestParam("images") MultipartFile[] images){

    List<Image> images2= new ArrayList<>();

    for (MultipartFile img : images) {
        if(img.getOriginalFilename().isEmpty()){
          //  continue;
        }else{
            String fileName =  System.currentTimeMillis()+"_"+StringUtils.cleanPath(img.getOriginalFilename());
            Path path = Paths.get(upload_directory + fileName);
            try {
                Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/files/download/")
                    .path(fileName)
                    .toUriString();
            
            Image img_save= new Image(image.getTitle(),  fileDownloadUri);
            img_save.setIsGallery(1);
            imageService.addImage(img_save);
        }
        
        
    }


return "redirect:/gallery/add";

}


     @GetMapping("/blog/add")
    public String addblog(Model model) {
        model.addAttribute("bl",new Blog());
        return "blog";
    }
        
        @PostMapping("/blog")
    public String saveBlog(@ModelAttribute Blog bl, Model model, @RequestParam("blogImage") MultipartFile blogImage){
  
        
         Long l= bl.getId();
        if(l != null) {
           Blog s= blogService.getBlog(l);

            bl.setDateupdated(s.getDateupdated());
        bl.setApproved(s.getApproved());
        
        if(blogImage.getOriginalFilename().isEmpty()){
      
       
         
        bl.setBImage(s.getBImage());
        
       blogService.addBlog(bl);
       

          //continue;
    } else{
            
              String fileName =  System.currentTimeMillis()+"_"+StringUtils.cleanPath(blogImage.getOriginalFilename());
        Path path = Paths.get(upload_directory + fileName);
        try {
            Files.copy(blogImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .toUriString();
    
        Image image= new Image(bl.getTitle(),  fileDownloadUri);
        bl.setBImage(image);
    

    }

  
       blogService.addBlog(bl);
    model.addAttribute("status", true);
    
    return "redirect:/AllBlog";
    
            
            
        }
        
        else{
           bl.setDateupdated(new Date());
   bl.setApproved(0);

    if(blogImage.getOriginalFilename().isEmpty()){
        //continue;
    }else{
        String fileName =  System.currentTimeMillis()+"_"+StringUtils.cleanPath(blogImage.getOriginalFilename());
        Path path = Paths.get(upload_directory + fileName);
        try {
            Files.copy(blogImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .toUriString();
    
        Image image= new Image(bl.getTitle(),  fileDownloadUri);
        bl.setBImage(image);
    
    }

  
       blogService.addBlog(bl);
    model.addAttribute("status", true);
    
    return "redirect:/blog/add";
    
    }
}
        
       
      @GetMapping("blog/edit/{id}")
    public String addEditBlog(Model model, @PathVariable Long id) {
        
        model.addAttribute("bl", blogService.getBlog(id));
        return "blog";
        
}
//    @PostMapping("/blog")
//    public String saveBlog(@ModelAttribute Blog bl, Model model, @RequestParam("blogImage") MultipartFile blogImage){
//   bl.setDateupdated(new Date());
//   bl.setApproved(0);
//
//    if(blogImage.getOriginalFilename().isEmpty()){
//        //continue;
//    }else{
//        String fileName =  System.currentTimeMillis()+"_"+StringUtils.cleanPath(blogImage.getOriginalFilename());
//        Path path = Paths.get(upload_directory + fileName);
//        try {
//            Files.copy(blogImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/files/download/")
//                .path(fileName)
//                .toUriString();
//    
//        Image image= new Image(bl.getTitle(),  fileDownloadUri);
//        bl.setBImage(image);
//    }
//
//  
//
//
//
//       blogService.addBlog(bl);
//    model.addAttribute("status", true);
//    
//    return "redirect:/blog";
//    
//    }
     @GetMapping("/AllBlog")
  
     public String ViewAllBlogs(Model model) {
        model.addAttribute("allPosts", blogService.getAllBlog());
        return "allPosts";
}
         @GetMapping("/unapprovedBlog")
    public String ViewUapprovedBlogs(Model model) {
        model.addAttribute("unapprovedPosts", blogService.getAllUnAprovedBlog());
        return "unapprovedPosts";
}
    @GetMapping("/ApprovedBlog")
    public String ViewApprovedBlogs(Model model) {
        model.addAttribute("approvedPosts", blogService.getAllAprovedBlog());
        return "approvedPosts";
}
    @GetMapping("blog/approve/{id}")
    public String approveBlog(Model model, @PathVariable Long id){
        Blog b=blogService.getBlog(id);
        if (b!= null){
        b.setApproved(1);
        blogService.updateBlog(b);
        }
          model.addAttribute("unapprovedPosts", blogService.getAllUnAprovedBlog());
        return "redirect:/unapprovedBlog";
    
       }
    @GetMapping("blog/unapprove/{id}")
    public String unApproveBlog(Model model, @PathVariable Long id){
        Blog b=blogService.getBlog(id);
        if (b!= null){
        b.setApproved(0);
        blogService.updateBlog(b);
        }
          model.addAttribute("approvedPosts", blogService.getAllAprovedBlog());
        return "redirect:/ApprovedBlog";
    
       }
    @GetMapping("blog/ap/approve/{id}")
    public String approveBlogP(Model model, @PathVariable Long id){
        Blog b=blogService.getBlog(id);
        if (b!= null){
        b.setApproved(1);
        blogService.updateBlog(b);
        }
          model.addAttribute("unapprovedPosts", blogService.getAllUnAprovedBlog());
        return "redirect:/AllBlog";
    
       }
  @GetMapping("blog/ap/unapprove/{id}")
    public String unApproveBlogP(Model model, @PathVariable Long id){
        Blog b=blogService.getBlog(id);
        if (b!= null){
        b.setApproved(0);
        blogService.updateBlog(b);
        }
          model.addAttribute("approvedPosts", blogService.getAllAprovedBlog());
        return "redirect:/AllBlog";
    
       }
    
    @ModelAttribute
    public void aboutSite(Model model){
    model.addAttribute("AboutInfo", aboutService.getAbout(1L));
    
    
    }
    
//        @GetMapping("/mailform")
//    public String mailForm(){
//        ContactForm con= contactFormService.getContactForm(id);
//        model.addAttribute("con", con);
//        
//        return "emailform";
//        
//    }

     @GetMapping("/mailform")
    public String sendMail(Model model){
        model.addAttribute("mf", new MailForm());
        return "emailform";
        
    }
    
    @GetMapping("/mailform/{id}")
    public String sendMail(Model model, @PathVariable Long id){
        ContactForm con= contactFormService.getContactForm(id);
        con.setReplied(1);
        contactFormService.updateContactForm(con);
        MailForm mf= new MailForm();
        mf.setMail_to(con.getEmail());  
        model.addAttribute("mf", mf);
        
        return "emailform";
        
    }
     @PostMapping("/mailform")
    public String saveMailResponse(@ModelAttribute MailForm mf, Model model){
    mf.setDateReplied(new Date());
   
   mailFormService.addMailForm(mf);
    model.addAttribute("status", true);
    
    return "redirect:/mailform";
    
    }
    
    
 
    }
    
   

