package org.biojava3.structure.quaternary.core;

import java.util.List;

import org.biojava.bio.structure.Structure;

public class ClusterProteinChains {
	private Structure structure = null;
	private QuatSymmetryParameters parameters = null;
	private ClusterMerger merger = null;

	public ClusterProteinChains(Structure structure, QuatSymmetryParameters parameters) {
		this.structure = structure;
		this.parameters = parameters;
		run();
	}
	
	public List<SequenceAlignmentCluster> getSequenceAlignmentClusters(double sequenceIdentityThreshold) {
		return merger.getMergedClusters(sequenceIdentityThreshold);
	}
	
	private void run () {
		// cluster protein entities
		ProteinSequenceClusterer clusterer = new ProteinSequenceClusterer(structure, parameters);
		List<SequenceAlignmentCluster> seqClusters = clusterer.getSequenceAlignmentClusters();
		if (seqClusters.size() == 0) {
			return;
		}
	
		// calculate pairwise aligment between protein clusters
		merger = new ClusterMerger(seqClusters, parameters);
		merger.calcPairwiseAlignments();
	}
}
