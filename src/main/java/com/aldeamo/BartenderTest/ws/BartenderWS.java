package com.aldeamo.BartenderTest.ws;

import com.aldeamo.BartenderTest.exceptions.IdNotFound;
import com.aldeamo.BartenderTest.response.BartenderResponse;
import com.aldeamo.BartenderTest.service.BartenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aldeamo.BartenderTest.entity.BartenderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.aldeamo.BartenderTest.util.Constants.SUCCESS_TYPE;

@Service
public class BartenderWS implements IBartenderWS {
    @Autowired BartenderService service;
    public BartenderWS(){}

    public BartenderResponse execute(int q, int id) throws IdNotFound {
        BartenderResponse response = new BartenderResponse();

        long time_started = System.currentTimeMillis();
        Optional<BartenderEntity> bartender = service.findById((long)id);

        if(!bartender.isPresent()){
            throw new IdNotFound("The provided id does not exist.");
        }

        String input_array = bartender.get().getInput_array();
        String[] parts = input_array.split(",");
        List<Integer> values = new ArrayList<>();

        for (String part : parts) {
            values.add(Integer.parseInt(part));
        }

        String solution = this.solve(q, values);

        response.setResult(solution);
        response.setStatus(SUCCESS_TYPE);
        response.setExecution_time((int)(System.currentTimeMillis()-time_started));

        return response;
    }


    public static String solve(int q, List<Integer> values){
        List<Integer> primes = primeNumbers(q);

        List<Integer> actualValues = new ArrayList<>(values);
        List<Integer> solution = new ArrayList<>();
        List<Integer> tmpValues = new ArrayList<>();

        for (int iQ = 0; iQ < q; iQ++) {
            // clear A from last Qi
            tmpValues.clear();
            for (int j = actualValues.size()-1; j >= 0; j--) {
                if (actualValues.get(j) % primes.get(iQ) == 0){
                    //add to solution
                    solution.add(actualValues.get(j));
                }else{
                    //rebuild A
                    tmpValues.add(0, actualValues.get(j));
                }
            }
            actualValues.clear();
            actualValues = new ArrayList<>(tmpValues);

        }
        // Adding rest of numbers from B
        solution.addAll(tmpValues);

        // Converting solution to String comma separated
        return solution.stream().map(String::valueOf).collect(Collectors.joining(","));
    }


    public static List<Integer> primeNumbers(int n) {
        List<Integer> primeNumbers = new ArrayList<>();
        int i = 1;
        while(primeNumbers.size()<n){
            if (isPrime(++i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }else if (n == 2) {
            return true;
        }else if (n % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(n); i += 2){
            if (n % i == 0) return false;
        }

        return true;

    }
}

