package org.openjpa.control;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.openjpa.control.exceptions.EntidadPreexistenteException;
import org.openjpa.control.exceptions.NoExisteEntidadException;
import org.openjpa.entidades.Asignatura;
import org.openjpa.entidades.Carrera;
import org.openjpa.entidades.Semestre;

public class AsignaturaControl implements Serializable {

    public AsignaturaControl(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public int insertar(Asignatura asignatura) throws EntidadPreexistenteException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(asignatura);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (buscaAsignatura(asignatura.getAsignaturaId()) != null) {
                throw new EntidadPreexistenteException("Asignatura " + asignatura + " ya existe en la base de datos.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return asignatura.getAsignaturaId();
    }

    public int editar(Asignatura asignatura) throws NoExisteEntidadException {
        EntityManager em = null;
        int aux = 0;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            asignatura = em.merge(asignatura);
            em.getTransaction().commit();
            aux = asignatura.getAsignaturaId();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asignatura.getAsignaturaId();
                if (buscaAsignatura(id) == null) {
                    throw new NoExisteEntidadException("La asignatura con el id " + id + " no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return aux;
    }

    public void eliminar(Integer id) throws NoExisteEntidadException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura asignatura;
            try {
                asignatura = em.getReference(Asignatura.class, id);
                asignatura.getAsignaturaId();
            } catch (EntityNotFoundException enfe) {
                throw new NoExisteEntidadException("La asignatura con el id " + id + " no existe.", enfe);
            }
            em.remove(asignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asignatura> buscaAsignaturas() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Asignatura> query = em.createNamedQuery("Asignatura.seleccionaTodos", Asignatura.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Asignatura buscaAsignatura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asignatura.class, id);
        } finally {
            em.close();
        }
    }
    
    public Carrera obtenerCarreraPorId(int carreraId) {
            EntityManager em = getEntityManager();
            try {
                return em.find(Carrera.class, carreraId);
            } finally {
                em.close();
            }
        }

    public Semestre obtenerSemestrePorId(int semestreId) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Semestre.class, semestreId);
        } finally {
            em.close();
        }
    }
    public int getTotalAsignatura() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Asignatura> rt = cq.from(Asignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List<Carrera> obtenerCarrerasOrdenadasPorId() {
        EntityManager em = getEntityManager();
        TypedQuery<Carrera> query = em.createQuery("SELECT c FROM Carrera c", Carrera.class);
        return query.getResultList();
    }

    public List<Semestre> obtenerSemestresOrdenadosPorId() {
        EntityManager em = getEntityManager();
        TypedQuery<Semestre> query = em.createQuery("SELECT s FROM Semestre s", Semestre.class);
        return query.getResultList();
    }
    
    public int obtenerCarreraId(String Carrera) {
        EntityManager em = getEntityManager();
        TypedQuery<Carrera> query = em.createQuery("SELECT c FROM Carrera c WHERE c.descripcion = :descripcion", Carrera.class);
        query.setParameter("descripcion", Carrera);
        Carrera carrera = query.getSingleResult();
        return carrera.getCarreraId();
    }

    public int obtenerSemestreId(String Semestre) {
        EntityManager em = getEntityManager();
        TypedQuery<Semestre> query = em.createQuery("SELECT s FROM Semestre s WHERE s.descripcion = :descripcion", Semestre.class);
        query.setParameter("descripcion", Semestre);
        Semestre carrera = query.getSingleResult();
        return carrera.getSemestreId();
    }
}
