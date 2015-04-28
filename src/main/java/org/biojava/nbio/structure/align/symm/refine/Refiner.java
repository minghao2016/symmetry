package org.biojava.nbio.structure.align.symm.refine;

import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.StructureException;
import org.biojava.nbio.structure.align.model.AFPChain;

/**
 * A method to refine the AFP alignment from one or more alternative self-alignments in order to make the subunits consistent.
 * @author Aleix Lafita
 *
 */
public interface Refiner {

	/**
	 * Returns a refined AFP alignment, where the subunit residues are aligned consistently in cycles.
	 * @param afpAlignments
	 * @param ca1
	 * @param ca2
	 * @param order
	 * @return AFPChain refined symmetry alignment
	 * @throws RefinerFailedException
	 * @throws StructureException
	 */
	AFPChain refine(AFPChain[] afpAlignments, Atom[] ca1, Atom[] ca2, int order) throws RefinerFailedException,StructureException;
	
}
