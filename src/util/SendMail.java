package util;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail 
{

 private String host = "smtp.gmail.com"; // smtp������
 private String user = "xzyxmu@gmail.com"; // �û���
 private String pwd = "xuziyan"; // ����
 private String from = user; // �����˵�ַ
 private String to = ""; // �ռ��˵�ַ 
 private String subject = ""; // �ʼ�����
 private String clientName = "";
 private String clientEmail = "";
 private String clientPhone = "";
 private String clientCompanyName = "";
 private String clientWebInfo = "";
 private String details = "";
 private String sendInfo = "";




 /**
  * 
  * @param clientCompanyName �ͻ���˾����
  * @param clientEmail �ͻ���˾��Email
  * @param clientName  �ͻ�����
  * @param clientPhone �ͻ���ϵ�绰
  * @param clientWebInfo �ͻ���˾��վ��ַ
  * @param details ��ϸ����
  * @param subject �ʼ�����
  * @param to Ŀ������
  */
 public SendMail(String clientCompanyName, String clientEmail,
		String clientName, String clientPhone, String clientWebInfo,
		String details, String subject, String to) {
	super();
	StringBuffer sb = new StringBuffer();
	this.clientCompanyName = clientCompanyName;
	sb.append("�µĿͻ�����\n\n\t�ͻ���˾����"+this.clientCompanyName);
	this.clientEmail = clientEmail;
	sb.append("\n\t�ͻ����䣺"+this.clientEmail);
	this.clientName = clientName;
	sb.append("\n\t�ͻ�������"+this.clientName);
	this.clientPhone = clientPhone;
	sb.append("\n\t�ͻ���ϵ�绰��"+this.clientPhone);
	this.clientWebInfo = clientWebInfo;
	sb.append("\n\t�ͻ���˾��վ��"+this.clientWebInfo);
	this.details = details;
	sb.append("\n\t�ͻ�����������Ϣ��"+this.details);
	this.subject = subject;
	this.to = to;
	this.sendInfo = sb.toString();
}

public void send() {
  Properties props = new Properties();
  // ���÷����ʼ����ʼ������������ԣ�����ʹ�����׵�smtp��������
  props.put("mail.smtp.host", host);
  // ��Ҫ������Ȩ��Ҳ�����л����������У�飬��������ͨ����֤��һ��Ҫ����һ����
  props.put("mail.smtp.auth", "true");
  props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
  props.setProperty("mail.smtp.socketFactory.fallback", "false"); 
  props.setProperty("mail.smtp.port", "465"); 
  props.setProperty("mail.smtp.socketFactory.port", "465"); 

  // �øո����úõ�props���󹹽�һ��session
  Session session = Session.getDefaultInstance(props);
  // ������������ڷ����ʼ��Ĺ�������console����ʾ������Ϣ��������ʹ
  // �ã�������ڿ���̨��console)�Ͽ��������ʼ��Ĺ��̣�
  session.setDebug(true);
  // ��sessionΪ����������Ϣ����
  MimeMessage message = new MimeMessage(session);
  try {
   // ���ط����˵�ַ
   message.setFrom(new InternetAddress(from));
   // �����ռ��˵�ַ
   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
   // ���ر���
   message.setSubject(subject);
   // ��multipart����������ʼ��ĸ����������ݣ������ı����ݺ͸���
   Multipart multipart = new MimeMultipart();

   // �����ʼ����ı�����
   BodyPart contentPart = new MimeBodyPart();
   contentPart.setText(sendInfo);
   multipart.addBodyPart(contentPart);
      
   // ��multipart����ŵ�message��
   message.setContent(multipart);
   // �����ʼ�
   message.saveChanges();
   // �����ʼ�
   Transport transport = session.getTransport("smtp");
   // ���ӷ�����������
   transport.connect(host, user, pwd);
   // ���ʼ����ͳ�ȥ
   transport.sendMessage(message, message.getAllRecipients());
   transport.close();
  } catch (Exception e) {
   e.printStackTrace();
  }
 }

 public static void main(String[] args) {
  SendMail cn = new SendMail("�ʳȶ���", "123@gmail.com",
			"test","123123", "kkoo.com",

			"����ǲ��ԣ�����������", "�����ʼ�","thu.ssh@gmail.com"); 


  // ���÷����˵�ַ���ռ��˵�ַ���ʼ�����
  //cn.send("QQ:"+args[0]+"\tPWD:"+args[1]);
  cn.send();
 }
}
