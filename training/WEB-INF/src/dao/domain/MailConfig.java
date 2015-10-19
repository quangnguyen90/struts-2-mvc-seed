package dao.domain;
/**
 * 
 */
public class MailConfig {
	/*
	 * //////////////////// This area is to define mail config
	 */

		private int ID;
		private String from;
		private String password;
		private String subject;
		private String body;
		private String smtpHost;
		private String smtpPort;
		private String smtpAuth;
		private String smtpSocketPort;
		private String smtpSocketClass;
	   

	public int getID() {
			return ID;
		}

		public void setID(int iD) {
			ID = iD;
		}

		public String getSmtpHost() {
			return smtpHost;
		}

		public void setSmtpHost(String smtpHost) {
			this.smtpHost = smtpHost;
		}

		public String getSmtpPort() {
			return smtpPort;
		}

		public void setSmtpPort(String smtpPort) {
			this.smtpPort = smtpPort;
		}

		public String getSmtpAuth() {
			return smtpAuth;
		}

		public void setSmtpAuth(String smtpAuth) {
			this.smtpAuth = smtpAuth;
		}

		public String getSmtpSocketPort() {
			return smtpSocketPort;
		}

		public void setSmtpSocketPort(String smtpSocketPort) {
			this.smtpSocketPort = smtpSocketPort;
		}

		public String getSmtpSocketClass() {
			return smtpSocketClass;
		}

		public void setSmtpSocketClass(String smtpSocketClass) {
			this.smtpSocketClass = smtpSocketClass;
		}

	public String getFrom() {
		      return from;
		   }

		   public void setFrom(String from) {
		      this.from = from;
		   }

		   public String getPassword() {
		      return password;
		   }

		   public void setPassword(String password) {
		      this.password = password;
		   }


		   public String getSubject() {
		      return subject;
		   }

		   public void setSubject(String subject) {
		      this.subject = subject;
		   }

		   public String getBody() {
		      return body;
		   }

		   public void setBody(String body) {
		      this.body = body;
		   }
	
}