package wfs.l2t.utility;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class LuceneSearcher {

	private String[] fields;
	private Path indexDirectory = FileSystems.getDefault().getPath(
			"IndexDirectory");
	private int total = 0;
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * constructor to new lucene search
	 * 
	 * @param fields
	 *            is array field to search in
	 * @param indexDirectory
	 *            is indexed directory
	 */
	public LuceneSearcher(String[] fields, String indexDirectory) {
		this.fields = fields;
		this.indexDirectory = FileSystems.getDefault().getPath(indexDirectory);
	}

	@SuppressWarnings("deprecation")
	public List<Document> search(String query, int skip, int limit) {
		TopDocs hits = null;
		List<Document> docs = new ArrayList<Document>();
		try {
			/** Searching */
			IndexReader directoryReader = DirectoryReader.open(FSDirectory
					.open(indexDirectory));
			IndexSearcher searcher = new IndexSearcher(directoryReader);
			Analyzer keywordAnalyzer = new StandardAnalyzer();

			/** MultiFieldQueryParser is used to search multiple fields */

			MultiFieldQueryParser mqp = new MultiFieldQueryParser(fields,
					keywordAnalyzer);

			/** search the given keyword */
			BooleanQuery finalQuery = new BooleanQuery();
			String[] terms = query.split(" ");
			for (String term : terms) {
				finalQuery.add(mqp.parse(term), BooleanClause.Occur.MUST);
			}

			/** run the query */
			hits = searcher.search(finalQuery, limit);
			setTotal(hits.totalHits);
			for (int i = skip; i < limit; i++) {
				docs.add(searcher.doc(hits.scoreDocs[i].doc));
			}
			System.out.println("Results found >> " + hits.totalHits);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docs;
	}
}
