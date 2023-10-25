package com.vermeg.dashboard.schedulers;

import com.vermeg.dashboard.entities.Application;
import com.vermeg.dashboard.repositories.ApplicationRepository;
import com.vermeg.dashboard.repositories.JobRepository;
import com.vermeg.dashboard.repositories.PipelineReposiotry;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Job;
import org.gitlab4j.api.models.Pipeline;
import org.gitlab4j.api.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GitlabSchedule {

    @Autowired
    private GitLabApi gitLabApi;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private PipelineReposiotry pipelineReposiotry;

    @Autowired
    private JobRepository jobRepository;

   @Scheduled(fixedDelay = 3600000)
    public void getProjects() throws GitLabApiException {


        for (Project project : gitLabApi.getProjectApi().getProjects()) {
            Application application = new Application();
            application.setId(project.getId());
            application.setName(project.getName());
            application = applicationRepository.save(application);

            List<Pipeline> pipeLines = gitLabApi.getPipelineApi().getPipelines(project.getId());

            for (Pipeline pipel : pipeLines) {
                com.vermeg.dashboard.entities.Pipeline pipeline = new com.vermeg.dashboard.entities.Pipeline();
                pipeline.setId(pipel.getId());


                pipeline.setStatus(pipel.getStatus().toValue());
                pipeline.setCreatedAt(pipel.getCreatedAt());
                pipeline.setSartedAt(pipel.getStartedAt());
                pipeline.setFinishedAt(pipel.getFinishedAt());
                pipeline.setDuration(pipel.getDuration());
                pipeline.setApplication(application);
                pipeline = pipelineReposiotry.save(pipeline);


                List<Job> jobs = gitLabApi.getJobApi().getJobsForPipeline(project.getId(), pipeline.getId());

                for (Job job : jobs) {
                    com.vermeg.dashboard.entities.Job job1 = new com.vermeg.dashboard.entities.Job();
                    job1.setId(job.getId());
                    job1.setName(job.getName());
                    job1.setStage(job.getStage());
                    job1.setStatus(job.getStatus().toValue());
                    job1.setCreatedAt(job.getCreatedAt());
                    job1.setStartedAt(job.getStartedAt());
                    job1.setFinishedAt(job.getFinishedAt());
                    job1.setPipeline(pipeline);
                    jobRepository.save(job1);
                }
            }
        }


    }


}
