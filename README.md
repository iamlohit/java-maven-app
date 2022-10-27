# Basic CI/CD Pipeline

1. Git hosts the application code and Dockerhub hosts the docker image created.
2. Jenkins orchestrates the app build, creating docker image, pushing docker image to Dockerhub.
3. Jenkins also deploys the app on AWS EC2.
4. All this is triggered with Github Webhook, when commit is made to Github repo/app code.