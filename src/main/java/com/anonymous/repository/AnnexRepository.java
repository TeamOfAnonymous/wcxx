package com.anonymous.repository;

import com.anonymous.domain.Annex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by WangZK on 2017/6/18.
 */
@Repository
public interface AnnexRepository extends JpaRepository<Annex, String> {
}
