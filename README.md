# 📜 Certificate Visualizer

## 📚 Project Overview

**Certificate Visualizer** is a platform designed to manage and showcase your certificates and achievements. It allows users to upload, store, and manage their certificates securely. Once uploaded, users can easily access and download their certificates anytime. The platform also includes a public profile feature, enabling users to share their certificate portfolio with others through a shareable link.

## 🚀 Features

- **User Authentication**: Register and log in to manage your certificates.
- **Certificate Management**: Upload and store certificates securely.
- **Public Profile**: Enable a public profile to share your certificate portfolio.
- **Email Notifications**: Receive email notifications upon registration and when setting up a public profile.
- **Download Certificates**: Download your certificates anytime from the platform.

## 🛠 Technologies Used

- **Backend**: Spring Boot
- **Frontend**: ReactJS
- **Database**: Amazon RDS
- **Storage**: Amazon S3
- **Secret Management**: AWS Secrets Manager
- **Deployment**: Amazon EC2
- **Email Services**: Amazon SNS and Lambda
- **Infrastructure as Code**: AWS CloudFormation

## 🏗 Architecture

1. **Frontend & Backend**:

   - Deployed on Amazon EC2 instances.
   - Backend manages business logic and interacts with the database and S3.
   - Frontend provides a user-friendly interface for certificate management.

2. **Database**:

   - Amazon RDS is used to store user details and certificate information securely.

3. **Storage**:

   - Certificates are stored in Amazon S3, ensuring secure and scalable storage.

4. **Secret Management**:

   - AWS Secrets Manager is used to store RDS credentials securely.

5. **Email Notifications**:

   - **AWS Lambda Functions**:
     - Function 1: Sends a welcome email upon user registration.
     - Function 2: Sends an email with the portfolio link when the user enables the public profile.
   - **Amazon SNS**: Manages email sending through the Lambda functions.

6. **Infrastructure**:
   - AWS CloudFormation is used for automated and consistent configuration of resources.

## 📂 How It Works

1. **User Registration**:

   - Users register on the platform and receive a welcome email.

2. **Managing Certificates**:

   - Users upload and store their certificates on the platform.
   - Certificates are saved in Amazon S3 and metadata is stored in Amazon RDS.

3. **Public Profile**:

   - Users can toggle the public profile feature to share their certificate portfolio.
   - Upon enabling, an email with the portfolio link is sent to the user.

4. **Email Notifications**:
   - Notifications are handled by AWS Lambda functions and SNS.

## 🛠 Setup and Deployment

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/yourusername/certificate-visualizer.git
2. **Frontend Setup**:

- Navigate to the frontend directory.
- Install dependencies:


    ```bash
    npm install
- Start the development server:

    ```bash
    npm start
3. **Backend Setup**:

- Navigate to the backend directory.
- Build and run the application:

    ```bash
    ./mvnw spring-boot:run
4. **Deploy**:

- Use AWS CloudFormation templates to deploy resources.
- Configure EC2, RDS, S3, and other AWS services.

## 📈 Future Improvements

- Enhance user interface with additional features.
- Implement advanced security measures.
- Optimize performance and scalability.

## 🙋‍♂️ Contact

- For any questions or feedback, please reach out to vaghanirutvik777@example.com.
