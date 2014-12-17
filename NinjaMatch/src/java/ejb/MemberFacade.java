/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.MemberAccount;
import model.UserAccount;

/**
 *
 * @author atan
 */
@Stateless
public class MemberFacade extends AbstractFacade<MemberAccount> {

    @PersistenceContext(unitName = "ninjamatchPU")
    private EntityManager em;

    @Resource
    private TimerService timerService;

    private String toEmail;
    private String fName;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public MemberFacade() {
        super(MemberAccount.class);
    }

    public List<MemberAccount> getStateMembers(MemberAccount m) {
        String jpql = "SELECT m FROM MemberAccount m WHERE m.address.state = :state";
        Query query = em.createQuery(jpql);
        query.setParameter("state", m.getAddress().getState());
        List<MemberAccount> res = query.getResultList();
        return res;
    }

    public boolean isUserNameExist(String username) {
        String jpql = "SELECT m FROM MemberAccount m WHERE m.userName = :username";
        Query query = em.createQuery(jpql);
        query.setParameter("username", username);
        List<MemberAccount> res = query.getResultList();
        return !res.isEmpty();
    }

    public MemberAccount findByUsername(String username) {
        try {
            String jpql = "SELECT c FROM MemberAccount c WHERE c.userName = :username";
            Query query = getEntityManager().createQuery(jpql, UserAccount.class);
            query.setParameter("username", username);
            return (MemberAccount) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public MemberAccount findByUsernamePassword(String username, String password) {
        try {
            String jpql = "SELECT c FROM MemberAccount c WHERE  c.userName = :username AND c.password = :password";
            Query query = getEntityManager().createQuery(jpql, UserAccount.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return (MemberAccount) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void create(MemberAccount entity) {
        getEntityManager().persist(entity);
        toEmail = entity.getEmail();
        fName = entity.getFirstName();
        Date date = entity.getBirthDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        ScheduleExpression greetings = new ScheduleExpression().dayOfMonth(day).month(month);
        timerService.createCalendarTimer(greetings, new TimerConfig(entity, true));
    }

    @Timeout
    public void sendGreetings(Timer timer) {
        final String username = "cs544ea@gmail.com";
        final String password = "cs544ea123";
        StringBuilder messageBody = new StringBuilder();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("cs544ea@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setSubject("Birthday Greetings");
            messageBody.append("Dear ").append(fName).append("\n\n");
            messageBody.append("This email send by Ninja Dating Site\n\n . Wishing you all the best for your birthday! ");
            messageBody.append("This is automated email please don't reply it\n\n");
            messageBody.append("Best Regards\n ");
            messageBody.append("Ninja Dating Developer");

            message.setText(messageBody.toString());
            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
