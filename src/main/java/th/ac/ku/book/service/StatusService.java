package th.ac.ku.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.book.model.Status;
import th.ac.ku.book.repository.StatusRepository;

import java.util.List;

@Service
public class StatusService {
    @Autowired
    StatusRepository statusRepository;

    public List<Status> getApprove(){
        return statusRepository.getApprove();
    }

    public List<Status> getPending(){
        return statusRepository.getPending();
    }

    public List<Status> getPreparing(){
        return statusRepository.getPreparing();
    }

    public List<Status> getDelivering(){
        return statusRepository.getDelivering();
    }

    public List<Status> getDelivered(){
        return statusRepository.getDelivered();
    }
}
