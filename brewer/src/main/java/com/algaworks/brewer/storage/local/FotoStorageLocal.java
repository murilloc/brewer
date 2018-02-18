package com.algaworks.brewer.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.brewer.storage.FotoStorage;

public class FotoStorageLocal implements FotoStorage {

	private static final Logger LOGGER = LoggerFactory.getLogger(FotoStorageLocal.class);

	private Path local;
	private Path localTemporario;

	public FotoStorageLocal() {
		this(getDefault().getPath(System.getenv("USERPROFILE"), ".brewerfotos"));
		criarPastas();
	}

	public FotoStorageLocal(Path path) {
		this.local = path;
		criarPastas();
	}

	@Override
	public byte[] recuperarFotoTemporaria(String nomeFoto) {
		
		try {
			return Files.readAllBytes(this.localTemporario.resolve(nomeFoto));
		} catch (IOException e) {
			
			throw new RuntimeException("Erro lendo arquivo da foto temporária",e);
		}
	}
	
	@Override
	public String salvarTemporariamente(MultipartFile[] files) {
		String novoNome = null;
		if (files != null && files.length > 0) {
			MultipartFile arquivo = files[0];
			novoNome = renomearArquivo(arquivo.getOriginalFilename());
			try {
				arquivo.transferTo(new File(
						this.localTemporario.toAbsolutePath().toString() + getDefault().getSeparator() + novoNome));
			} catch (IOException e) {

				throw new RuntimeException("Erro ao salvar a foto na pasta temporária", e);
			}

		}

		return novoNome;
	}

	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			this.localTemporario = getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Pastas criadas para salvar foto.");
				LOGGER.debug("Pasta default: " + this.local.toAbsolutePath());
				LOGGER.debug("Pasta temporária: " + this.localTemporario.toAbsolutePath());
			}
		} catch (IOException e) {

			throw new RuntimeException("Erro ao criar pasta para salvar fotos, e");
		}
	}

	private String renomearArquivo(String nomeOriginal) {
		String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(String.format("Nome original: s% , novo nome: %s", nomeOriginal, novoNome));
		}

		return novoNome;
	}


}
