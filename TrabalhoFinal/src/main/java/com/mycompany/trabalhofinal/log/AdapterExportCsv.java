/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal.log;

import com.mycompany.trabalhofinal.model.Usuario;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Mario
 */
public class AdapterExportCsv implements IAdapterExport {

    @Override
    public void escrever(Usuario usuarioLogado, String operacao, String nome) throws IOException {

        List<String[]> lista = readCSVFile();
        if (lista != null) {
            lista.add(new String[]{operacao, nome, LocalDateTime.now().toString(), usuarioLogado != null ? usuarioLogado.getNome() : null});
            Writer writer = Files.newBufferedWriter(Paths.get("log.csv"));
            CSVWriter csvWriter = new CSVWriter(writer);
            csvWriter.writeAll(lista);
            csvWriter.flush();
            writer.close();
        } else {
            String[] cabecalho = {"operacao", "nome", "data", "usuarioLogado"};
            var linha = new String[]{operacao, nome, LocalDateTime.now().toString(), usuarioLogado != null ? usuarioLogado.getNome() : null};
            Writer writer = Files.newBufferedWriter(Paths.get("log.csv"));
            CSVWriter csvWriter = new CSVWriter(writer);

            csvWriter.writeNext(cabecalho);
            csvWriter.writeNext(linha);
            csvWriter.flush();
            writer.close();
        }

    }

    public List<String[]> readCSVFile() {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader("log.csv"));
        } catch (FileNotFoundException e) {
        }

        if (reader == null) {
            return null;
        }
        List<String[]> allRows = null;
        try {
            allRows = reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allRows;
    }

}
