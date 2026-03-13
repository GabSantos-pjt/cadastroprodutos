package Controller;

import Model.produtoModel;
import repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class produtoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // POST - cadastrar produto
    @PostMapping
    public produtoModel cadastrarProduto(@RequestBody produtoModel produto) {
        return produtoRepository.save(produto);
    }

    // GET - listar todos os produtos
    @GetMapping
    public List<produtoModel> listarProdutos() {
        return produtoRepository.findAll();
    }

    // GET - buscar produto por ID
    @GetMapping("/{id}")
    public Optional<produtoModel> buscarProduto(@PathVariable Long id) {
        return produtoRepository.findById(id);
    }

    // PUT - atualizar produto
    @PutMapping("/{id}")
    public produtoModel atualizarProduto(@PathVariable Long id, @RequestBody produtoModel produtoAtualizado) {

        Optional<produtoModel> produtoExistente = produtoRepository.findById(id);

        if (produtoExistente.isPresent()) {

            produtoModel produto = produtoExistente.get();

            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setQuantidadeEmEstoque(produtoAtualizado.getQuantidadeEmEstoque());
            produto.setStatus(produtoAtualizado.getStatus());

            return produtoRepository.save(produto);
        }

        return null;
    }

    // DELETE - remover produto
    @DeleteMapping("/{id}")
    public void removerProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }
}