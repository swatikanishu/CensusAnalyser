package com.censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
;
    public class CensusAnalyser {


        public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
            String[] splitPath = csvFilePath.split("[.]");
            String extensionType = splitPath[splitPath.length - 1];
            if (!extensionType.equals("csv")) {
                throw new CensusAnalyserException("Invalid Extension", CensusAnalyserException.ExceptionType.CENSUS_TYPE_PROBLEM);
            }
            try {
                Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
                CsvToBeanBuilder<IndiaCensusCsv> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
                csvToBeanBuilder.withType(IndiaCensusCsv.class);
                csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
                CsvToBean<IndiaCensusCsv> csvToBean = csvToBeanBuilder.build();
                Iterator<IndiaCensusCsv> censusCSVIterator = csvToBean.iterator();
                int namOfEateries = 0;
                while (censusCSVIterator.hasNext()) {
                    namOfEateries++;
                    IndiaCensusCsv censusData = censusCSVIterator.next();
                }
                return namOfEateries;
            } catch (IOException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            }catch (RuntimeException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.WRONG_HEADER);
            }
        }
    }