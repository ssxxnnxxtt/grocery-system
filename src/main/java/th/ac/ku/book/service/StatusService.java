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

    public List<Status> getAcceptStatus(){
        return statusRepository.getAcceptStatus();
    }
}
