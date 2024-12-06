# Study Plus Application for Research Insititutions

# 1. Problem Statement

## Challenges
The Makerere University - Johns Hopkins University (MU-JHU) Research Collaboration faces several challenges in managing health research studies efficiently, including:

- **Complex Study Coordination**: Numerous ongoing studies lack a centralized system, making it difficult for Study Coordinators to oversee progress, track participants, and manage study resources.

- **Data Accuracy Issues**: Without real-time data oversight, inaccuracies can arise, impacting the integrity of studies.

- **Inefficient Participant Management**: Tracking participant details across studies is difficult, leading to delays in recruitment, tracking, and follow-ups.

- **Communication Gaps**: Fragmented communication between Study Coordinators and participants slows data collection and feedback loops.

- **Role-Specific Needs**: Current systems don’t account for role-specific functionalities for coordinators, participants, and the research management team.

## Project Objective
StudyPlus aims to develop a streamlined, efficient platform tailored to health research management, with the Study Coordinator acting as the system’s Admin. This role includes the ability to manage studies, oversee participant tracking, and ensure data accuracy and smooth communication. The platform is designed to facilitate improved study coordination and data management. 


- **Study Management**: Enables the Study Coordinator to monitor real-time progress for each study, with clear statuses and notifications for each stage (Ongoing, Completed, etc.).

**Participant Management**: Streamlines participant registration and data management for efficient recruitment and follow-up, helping coordinators maintain accurate and up-to-date information.

**Data Accuracy and Reporting**: Centralizes data input, enhancing accuracy for reporting and analysis needs across studies.

**Role-Specific Access**: Provides a tailored interface and specific permissions for the Study Coordinator (Admin) and Participants, ensuring ease of access and navigation for all users.

## Desired Outcome

Improve study management and data accuracy by creating a well-structured platform that meets the needs of coordinators and participants. This includes simplified participant registration, real-time study progress tracking, and robust reporting to support MU-JHU’s mission in health research and services, leading to improved study management and outcomes.

# 2. Requirements Analysis

# 2.1 Functional Requirements

  ***Study Coordinator (Admin)***: Has full control over platform data, including the ability to:
   
        - Register, manage, and update studies.
        
        - Add, update, and manage participant details.
        
        - Perform CRUD operations across studies and participants.
        
   ***Participant***: 
        - View study details
        
        - Receives notifications (enrollment confirmation, study end date etc)
        

# 2.2 Non-functional Requirement
    - **Security**: System must be secure
    - **Security**: Secure authentication and authorization for study coordinators and participants
    

    **Avaliability**: System must be avaliable at all times through cloud deployment

# 3. Architecture

![StudyPlus_Architecture](https://github.com/user-attachments/assets/541d2232-e753-4f00-9d84-2e059bed4602)

# 4. UML Class Diagram

![newStudyPlus](https://github.com/user-attachments/assets/f0434061-622a-4f80-b44e-f4dd12888588)

# 4. ER Diagram

![StudyPlus_ER Diagram](https://github.com/user-attachments/assets/c362d063-874e-48c3-b8cc-662d6edbc20d)

# 5. Software Setup Instructions
# 5.1 Local Installation
      Install JDK 21 from the following link:
         https://www.oracle.com/java/technologies/downloads

      Download or clone this repository:
         git clone https://github.com/smwemeke/StudyPlus-App.git

      Run PostgreSQL
         Configure PostgreSQL in application.prooerties file:
![image](https://github.com/user-attachments/assets/0ab118e8-17d1-4550-a57f-a0c4fa0e3b95)

     Package application
        mvn clean package

     Run the application
       java -jar target/studyplus-1.0.1.jar
![image](https://github.com/user-attachments/assets/bf177996-f006-426e-ab20-f954d0ae5716)

# 5.2 Setting up application on Docker
     Package springboot application
        mvn clean package
     
     Build image
        docker build -t smwemeke/studyplusapp:1.0.1
     Start application and its services 
        docker-compose up -d

# 5.3 Setting up on Azure

      Create Dockerfile for application to build image

      Create Docker compose file including postgres database configuration  and application

      Create jar file (make sure to comment out database information in application.properties file. If not, it does not work when deploying to Azure)

      Package the application
           mvn clean package
      Build docker image for application
           docker build -t smwemeke/studyplus:1.0.1
      Push image to Docker Hub (https://hub.docker.com/)
           docker push smwemeke/studyplus:1.0.1
      create App service

  ![image](https://github.com/user-attachments/assets/8fadfb8a-d275-4af3-8f77-75873f982d16)

      In Deployment -> Deployment Center, configure

         Container type: Docker Compose

         Registry source: Docker Hub

         Copy and paste the Docker compose configurations into the Config textbox
             ![image](https://github.com/user-attachments/assets/d8996973-f61e-42d2-84b9-c3c3e63b9ac5)

# 6. Links
Project Demo for deploymeny on Azure
https://mum0-my.sharepoint.com/:v:/r/personal/smwemeke_miu_edu/Documents/Microsoft%20Teams%20Chat%20Files/studyplus-Container_Deployment.mp4?csf=1&web=1&e=HUEoxW




        
