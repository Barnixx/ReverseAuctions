package pl.reverseAuctions.offer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OfferService {

    void save(Offer offer);

    Offer getById(Long id);

    List<Offer> getAll();

    void delete(Long id);

    void delete(Offer offer);

}
