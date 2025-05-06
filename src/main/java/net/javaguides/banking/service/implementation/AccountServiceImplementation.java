package net.javaguides.banking.service.implementation;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.exception.AccountException;
import net.javaguides.banking.mapper.Accountmapper;
import net.javaguides.banking.repository.AccountRepository;
import net.javaguides.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImplementation implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImplementation(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = Accountmapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return Accountmapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account= accountRepository.findById(id)
                .orElseThrow(() ->new AccountException("Account doesn't exist"));
        return Accountmapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account= accountRepository.findById(id)
                .orElseThrow(() ->new AccountException("Account doesn't exist"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return Accountmapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()->new AccountException("Account doesn't exist"));
        if(account.getBalance()< amount) {
            throw new RuntimeException("Insufficient amount");
        }
        double total  = account.getBalance() - amount;
        account.setBalance(total);
        Account  savedAccount  = accountRepository.save(account);
        return Accountmapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> Accountmapper.mapToAccountDto(account))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account  = accountRepository
                .findById(id)
                .orElseThrow(()-> new AccountException("Account does not exists"));
        accountRepository.deleteById(id);

    }
}
